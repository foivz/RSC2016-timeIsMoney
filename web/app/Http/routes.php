<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

// Social
Route::get('auth/{provider}', 'Auth\AuthController@redirectToProvider')
    ->name('socialAuth');
Route::get('auth/{provider}/check', 'Auth\AuthController@handleProviderCallback')
    ->name('socialAuthCallback');
Route::get('/login', 'Auth\AuthController@login');
Route::get('/logout', 'Auth\AuthController@logout');

// Mobile
Route::group(['prefix'=> '/api'], function() {
    Route::post('/login', 'API\AuthController@login');

    Route::get('/events', 'API\Events\EventsController@listAll');
    Route::get('/question/{id}', 'API\Events\EventsController@question');
    Route::get('/events/current', 'API\Events\EventsController@listCurrent');

    Route::group(['middleware'=> 'auth.api'], function() {
        Route::get('/team/{id}/members', 'API\Teams\TeamsController@getTeamMembers');
        Route::post('/team', 'API\Teams\TeamsController@create');
        Route::post('/team/join', 'API\Teams\TeamsController@joinTeam');
        Route::get('/event/{eventId}/question', 'API\Events\GameController@getCurrentQuestion');
        Route::post('/answer', 'API\Events\GameController@answer');
    });
    Route::get('/event/{eventId}/scoreboard', 'API\Events\GameController@getScore')->name('event.score');

});

Route::group(['middleware' => ['web', 'auth']], function() {
    Route::resource('moderator/event', 'Moderator\\EventController');
    Route::resource('moderator/quiz', 'Moderator\\QuizController');
    Route::resource('moderator/question', 'Moderator\\QuestionController');
    Route::resource('moderator/answer', 'Moderator\\AnswerController');

    Route::get('moderator/moderate', 'Moderator\\EventModerationController@listOpenQuizzes');
    Route::get('moderator/lobby/{eventId}', 'Moderator\\EventModerationController@lobby')->name('lobby');
    Route::get('moderator/lobby/{eventId}/members', 'Moderator\\EventModerationController@getTeams')->name('lobby.teams');

    Route::get('moderator/event/{eventId}/start', 'Moderator\\EventModerationController@startGame')->name('event.start');
    Route::get('moderator/event/{eventId}/end', 'Moderator\\EventModerationController@endGame')->name('event.end');
});