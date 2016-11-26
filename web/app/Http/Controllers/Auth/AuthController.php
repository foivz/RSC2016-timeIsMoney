<?php

namespace App\Http\Controllers\Auth;

use App\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Laravel\Socialite\Facades\Socialite;
use Validator;
use App\Http\Controllers\Controller;

class AuthController extends Controller
{
    private $supportedProviders = [
        'facebook',
        'google',
    ];

    /**
     * Redirect the user to the GitHub authentication page.
     *
     * @param $provider
     * @return Response
     */
    public function redirectToProvider($provider)
    {
        if(in_array($provider, $this->supportedProviders)) {
            return Socialite::driver($provider)->redirect();
        }
        return response('Provider not found.', 404);
    }

    /**
     * Obtain the user information from GitHub.
     *
     * @param $provider
     * @return Response
     */
    public function handleProviderCallback(Request $request, $provider)
    {
        if(in_array($provider, $this->supportedProviders) == false)
            return response('Provider not found.', 404);
        // Fetch user data
        $socialUser = Socialite::driver($provider)->user();

        $user = User::where('email', $socialUser->getEmail())->first();
        if($user === null) {
            $user = new User();
            $user->name =  $socialUser->getName();
            $user->email = $socialUser->getEmail();
            $user->avatar_url = $socialUser->getAvatar();
            $user->password = Hash::make(str_random(64));
            $user->save();
        }
        if($provider == 'google') {
            $user->google_id = $socialUser->getId();
        }
        if($provider == 'facebook') {
            $user->facebook_id = $socialUser->getId();
        }
        $user->generateApiKey();
        // Log in the user
        Auth::login($user);
        return redirect('moderator/event');
    }

    public function login()
    {
        return view('auth.login');
    }

    public function logout()
    {
        Auth::logout();
        return redirect('/');
    }

}
