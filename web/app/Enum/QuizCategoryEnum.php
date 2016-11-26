<?php


namespace App\Enum;


class QuizCategoryEnum
{
    const QUIZ_TECH = 0;
    const QUIZ_HISTORY = 1;
    const QUIZ_SPORT = 2;
    const QUIZ_ART = 3;
    const QUIZ_CULTURE = 4;

    public static function getStatusCaption($statusId) {
        switch($statusId) {
            case static::QUIZ_TECH:
                return "Technology";
            case static::QUIZ_HISTORY:
                return "History";
            case static::QUIZ_SPORT:
                return "Sport";
            case static::QUIZ_ART:
                return "Art";
            case static::QUIZ_CULTURE:
                return "Culture";
        }
    }
}