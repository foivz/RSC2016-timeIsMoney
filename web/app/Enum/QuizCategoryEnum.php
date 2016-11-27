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

    public static function getCategoryColor($categoryId) {
        switch($categoryId) {
            case static::QUIZ_TECH:
                return "#3498db";
            case static::QUIZ_HISTORY:
                return "#e67e22";
            case static::QUIZ_SPORT:
                return "#34495e";
            case static::QUIZ_ART:
                return "#e74c3c";
            case static::QUIZ_CULTURE:
                return "#9b59b6";
        }
    }
}