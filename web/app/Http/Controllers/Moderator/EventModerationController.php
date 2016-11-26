<?php

namespace App\Http\Controllers\Moderator;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Response\APIResponse;
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
}
