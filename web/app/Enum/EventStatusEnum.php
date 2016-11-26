<?php


namespace App\Enum;


class EventStatusEnum
{
    const STATUS_UNPUBLISHED = 0;
    const STATUS_SCHEDULED = 1;
    const STATUS_OPEN = 2;
    const STATUS_CLOSED = 3;

    public static function getStatusCaption($statusId) {
        switch($statusId) {
            case static::STATUS_UNPUBLISHED:
                return "Unpublished";
            case static::STATUS_SCHEDULED:
                return "Scheduled";
            case static::STATUS_OPEN:
                return "Open";
            case static::STATUS_CLOSED:
                return "Closed";
        }
    }
}