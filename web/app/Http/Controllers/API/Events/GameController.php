<?php


namespace App\Http\Controllers\API\Events;

use App\Answer;
use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Controllers\API\BaseApiController;
use App\Http\Response\APIResponse;
use App\Question;
use App\Team;
use App\TeamMember;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class GameController extends BaseApiController
{
    public function getCurrentQuestion($eventId)
    {
        $event = Event::find($eventId);
        if($event->status == EventStatusEnum::STATUS_FINISHED) {
            return new APIResponse(400, [], ['Quiz is finished.']);
        }
        if($event->status != EventStatusEnum::STATUS_STARTED) {
            return new APIResponse(403, [], ['Quiz not yet started.']);
        }
        $questions = Question::where('quiz_id', $event->quiz->id)->with('answers')->get();
        return new APIResponse(200, $questions[$event->current_question-1]);
    }


    public function answer(Request $request)
    {
        $teamId = $request->get('team_id');
        $answerId = $request->get('answer_id');
        $answer = Answer::find($answerId);

        if($answer->correct) {
            $member = TeamMember::where('team_id', $teamId)
                ->where('user_id', $this->getUser($request)->id)
                ->first();
            $member->score++;
            $member->save();
        }
        return new APIResponse(200);
    }

    public function getScore($eventId)
    {
        $teams = Team::where('event_id', $eventId)->pluck('team_id');

        $result = DB::table('team_members')
            ->select('team_id', DB::raw('SUM(score) as score_sum'))
            ->whereIn('team_id', $teams)
            ->groupBy('team_id')
            ->get();

        return $result;
    }
}