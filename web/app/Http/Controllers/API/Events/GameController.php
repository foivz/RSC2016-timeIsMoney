<?php


namespace App\Http\Controllers\API\Events;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Controllers\API\BaseApiController;
use App\Http\Response\APIResponse;
use App\Question;
use App\TeamMember;
use Carbon\Carbon;

class GameController extends BaseApiController
{
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


    public function answer($eventId, $answerId)
    {
        //$member = TeamMember::where('event_id')
        // ToDo: implement
    }
}