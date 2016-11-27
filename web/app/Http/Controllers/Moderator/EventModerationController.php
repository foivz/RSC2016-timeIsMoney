<?php

namespace App\Http\Controllers\Moderator;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Response\APIResponse;
use App\Question;
use App\Team;
use App\TeamMember;
use Illuminate\Support\Facades\DB;
use Session;

class EventModerationController extends Controller
{
    public function listOpenQuizzes()
    {
        $event = Event::where('status', EventStatusEnum::STATUS_OPEN)->get();
        return view('moderator.moderation.index', compact('event'));
    }

    public function lobby($eventId)
    {
        $event = Event::find($eventId);
        return view('moderator.moderation.lobby')->with('event', $event);
    }

    public function getTeams($eventId)
    {
        $teams = Team::where('event_id', $eventId)->with('members')->get();
        return new APIResponse(200, $teams);
    }

    public function startGame($eventId)
    {
        $event = Event::find($eventId);
        $event->status = EventStatusEnum::STATUS_STARTED;
        $event->current_question++;

        $questions = Question::where('quiz_id', $event->quiz->id)->with('answers')->get();
        if(count($questions) < $event->current_question) {
            if($this->isTieBreaker($eventId)) {
                return redirect(route('event.tiebreaker', ['eventId' => $event->id]));
            }
            return redirect(route('event.end', ['eventId' => $event->id]));
        }
        $event->save();

        return view('moderator.moderation.game')
            ->with('event', $event)
            ->with('question', $questions[$event->current_question-1]);
    }


    public function endGame($eventId)
    {
        if($this->isTieBreaker($eventId)) {
            $result = $this->getTiebreakerTeams($eventId);
            $teams = [$result[0]->team_id, $result[1]->team_id];

            $result = DB::table('team_members')
                ->select('team_id', 'team_name', DB::raw('SUM(tiebreaker_score) as tiebreaker_score_sum'))
                ->whereIn('team_id', $teams)
                ->groupBy('team_id')
                ->leftJoin('teams', 'teams.id', '=', 'team_members.team_id')
                ->get();
            $score = array();
            foreach ((array)$result as $key => $row)
            {
                $score[$key] = $row->tiebreaker_score_sum;
            }
            array_multisort($score, SORT_DESC, $result);

            $team = TeamMember::where('team_id', $result[0]->team_id)->first();
            $team->score += 10;
            $team->save();
        }
        $event = Event::find($eventId);
        $event->status = EventStatusEnum::STATUS_FINISHED;
        $event->save();
        return view('moderator.moderation.end')
            ->with('event', $event);
    }



    // TIEBREAKER

    public function tiebreaker($eventId)
    {
        $event = Event::find($eventId);
        $event->status = EventStatusEnum::STATUS_FINISHED;
        $event->save();
        return view('moderator.moderation.tiebreaker')
            ->with('event', $event);
    }

    public function getTiebreakerTeams($eventId)
    {
        $teams = Team::where('event_id', $eventId)->pluck('id');

        $result = DB::table('team_members')
            ->select('team_id', 'team_name', DB::raw('SUM(score) as score_sum'))
            ->whereIn('team_id', $teams)
            ->groupBy('team_id')
            ->leftJoin('teams', 'teams.id', '=', 'team_members.team_id')
            ->get();

        $score = array();
        foreach ((array)$result as $key => $row)
        {
            $score[$key] = $row->score_sum;
        }
        array_multisort($score, SORT_DESC, $result);

        return $result;
    }

    private function isTieBreaker($eventId)
    {
        $result = $this->getTiebreakerTeams($eventId);

        if(count($result) < 2) {
            return false;
        }
        if($result[0]->score_sum == $result[1]->score_sum) {
            return true;
        }
        return false;
    }

    public function getTiebreakerScore($eventId)
    {
        $result = $this->getTiebreakerTeams($eventId);
        $teams = [$result[0]->team_id, $result[1]->team_id];

        $result = DB::table('team_members')
            ->select('team_id', 'team_name', DB::raw('SUM(tiebreaker_score) as tiebreaker_score_sum'))
            ->whereIn('team_id', $teams)
            ->groupBy('team_id')
            ->leftJoin('teams', 'teams.id', '=', 'team_members.team_id')
            ->get();

        return new APIResponse(200, $result);
    }
}
