<?php

namespace App\Http\Controllers\Moderator;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use Session;

class EventModerationController extends Controller
{
    public function listOpenQuizzes()
    {
        $quiz = Event::where('status', EventStatusEnum::STATUS_OPEN)->get();
        return view('moderator.moderation.index', compact('quiz'));
    }

    public function moderateEvent($eventId)
    {
        $event = Event::find($eventId);

    }
}
