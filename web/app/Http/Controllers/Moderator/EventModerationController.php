<?php

namespace App\Http\Controllers\Moderator;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Response\APIResponse;
use App\Question;
use App\Team;
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

    public function getCurrentQuestion($eventId)
    {
        $event = Event::find($eventId);
        if($event->status != EventStatusEnum::STATUS_FINISHED) {
            return new APIResponse(204, [], ['Quiz is finished.']);
        }
        if($event->status != EventStatusEnum::STATUS_STARTED) {
            return new APIResponse(403, [], ['Quiz not yet started.']);
        }
        $questions = Question::where('quiz_id', $event->quiz->id)->with('answers')->get();
        return new APIResponse(200, $questions[$event->current_question-1]);
    }

    public function startGame($eventId)
    {
        $event = Event::find($eventId);
        $event->status = EventStatusEnum::STATUS_STARTED;
        $event->current_question++;

        $questions = Question::where('quiz_id', $event->quiz->id)->with('answers')->get();
        if(count($questions) < $event->current_question) {
            return redirect(route('event.end', ['eventId' => $event->id]));
        }
        $event->save();

        return view('moderator.moderation.game')
            ->with('event', $event)
            ->with('question', $questions[$event->current_question-1]);
    }

    public function endGame($eventId)
    {
        $event = Event::find($eventId);
        $event->status = EventStatusEnum::STATUS_FINISHED;
        return "ended";
    }
}
