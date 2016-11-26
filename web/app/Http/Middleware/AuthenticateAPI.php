<?php

namespace App\Http\Middleware;

use App\API\APIResponse;
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
            return new APIResponse(401, [], ['X-Authorization header not found.']);
        }
        if (User::where('api_key', $apiKey)->first() == null) {
            return new APIResponse(401, [], ['User with the API key not found.']);
        }

        return $next($request);
    }
}
