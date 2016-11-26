<?php
namespace App\Http\Controllers\API;


use App\Http\Controllers\Controller;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class BaseApiController extends Controller
{

    /**
     * @param Request $request
     * @return null
     */
    public function getUser(Request $request)
    {
        try {
            $user = User::where('api_key', $request->header('X-Authorization'))->first();
            return $user;
        }
        catch(ModelNotFoundException $e) {
            return null;
        }
    }

}