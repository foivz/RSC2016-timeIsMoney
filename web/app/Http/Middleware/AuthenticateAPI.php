<?php

namespace App\Http\Middleware;

use App\Http\Response\APIResponse;
use App\User;
use Closure;

class AuthenticateAPI
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @param  string|null  $guard
     * @return mixed
     */
    public function handle($request, Closure $next, $guard = null)
    {
        $apiKey = $request->header('X-Authorization');
        if ($apiKey == null) {
            return response('X-Authorization header not found.', 401);
        }
        if (User::where('api_key', $apiKey)->first() == null) {
            return response('User with the API key not found.', 401);
        }

        return $next($request);
    }
}
