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

Route::auth();

// Mobile
Route::group(['prefix'=> '/api'], function() {
    Route::post('/login', 'API\AuthController@login');

    Route::get('/events', 'API\Events\EventsController@listAll');
    Route::get('/events/current', 'API\Events\EventsController@listCurrent');

    Route::group(['middleware'=> 'auth.api'], function() {
    });
});