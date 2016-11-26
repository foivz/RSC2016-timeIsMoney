<?php
namespace App\Http\Controllers\API;

use App\Http\Response\APIResponse;
use App\User;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;

class AuthController extends BaseApiController
{

    /*
     * PUBLIC
     * Validates the username and password and returns an API key.
     */
    /**
     * @param Request $request
     * @return APIResponse
     */
    public function login(Request $request)
    {
        if(in_array($request->get('provider'), ['google', 'facebook']) === false) {
            return new APIResponse(401, [], [], ['Supported providers are "google" and "facebook".']);
        }

        $socialUser = Socialite::driver($request->get('provider'))
            ->userFromToken($request->get('access_token'));

        $user = User::where($request->get('provider') . '_id')->first();
        if($user === null) {
            $user = new User();
            if($request->get('provider') == 'google') {
                $user->google_id = $socialUser->getId();
            }
            if($request->get('provider') == 'facebook') {
                $user->facebook_id = $socialUser->getId();
            }
            $user->name =  $socialUser->getName();
            $user->email = $socialUser->getEmail();
            $user->avatar_url = $socialUser->getAvatar();
            $user->save();
        }

        return new APIResponse(200, $user->toArray());
    }

    /*
     * PRIVATE
     * Returns info about the authenticated user.
     */
    public function show(Request $request)
    {
        $user = $this->getUser($request);
        return new APIResponse(200, $user);
    }

}