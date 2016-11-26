<?php

return [

    /*
    |--------------------------------------------------------------------------
    | Third Party Services
    |--------------------------------------------------------------------------
    |
    | This file is for storing the credentials for third party services such
    | as Stripe, Mailgun, Mandrill, and others. This file provides a sane
    | default location for this type of information, allowing packages
    | to have a conventional place to find your various credentials.
    |
    */

    'mailgun' => [
        'domain' => env('MAILGUN_DOMAIN'),
        'secret' => env('MAILGUN_SECRET'),
    ],

    'ses' => [
        'key' => env('SES_KEY'),
        'secret' => env('SES_SECRET'),
        'region' => 'us-east-1',
    ],

    'sparkpost' => [
        'secret' => env('SPARKPOST_SECRET'),
    ],

    'stripe' => [
        'model' => App\User::class,
        'key' => env('STRIPE_KEY'),
        'secret' => env('STRIPE_SECRET'),
    ],

    'google' => [
        'client_id' => '981151316904-cn6p9f0ea58ue401la9hhb8dhbnbs3bm.apps.googleusercontent.com',
        'client_secret' => 'uj6oLRrW34qwAg3nSFN3D3Sz',
        'redirect' => env('APP_URL') . '/auth/google/check'
    ],

    'facebook' => [
        'client_id' => '222091578216716',
        'client_secret' => '16b3bf25c9132a62769e79715f2ea12a',
        'redirect' => env('APP_URL') . '/auth/facebook/check'
    ],

];
