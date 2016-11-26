<?php


namespace App\Http\Controllers\API\Events;

use App\Enum\EventStatusEnum;
use App\Event;
use App\Http\Controllers\API\BaseApiController;
use App\Http\Response\APIResponse;
use App\Question;
use Carbon\Carbon;

class EventsController extends BaseApiController
{
    public function listAll()
    {
        $events = Event::where('start_at', '>', Carbon::now()->subHour(1))->get();
        return new APIResponse(200, $events);
    }

    public function listCurrent()
    {
        $events = Event::where('status', EventStatusEnum::STATUS_OPEN)->get();
        return new APIResponse(200, $events);
    }

    //ToDo: REMOVE
    public function question($id) {
        return Question::where('id', $id)->with('answers')->first();
    }
}