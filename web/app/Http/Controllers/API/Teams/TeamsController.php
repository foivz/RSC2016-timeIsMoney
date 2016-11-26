<?php


namespace App\Http\Controllers\API\Teams;

use App\Http\Controllers\API\BaseApiController;
use App\Http\Response\APIResponse;
use App\Team;
use App\User;
use Illuminate\Http\Request;

class TeamsController extends BaseApiController
{
    /**
     * @param Request $request
     * @return APIResponse
     */
    public function create(Request $request)
    {
        if($request->has(['event_id', 'team_name']) == false) {
            return new APIResponse(401, [], [], ["Missing 'event_id' or 'team_name' parameters."]);
        }

        $team = new Team([
            'event_id' => $request->get('event_id'),
            'team_name' => $request->get('team_name'),
        ]);
        $team->save();

        $team->addMember($this->getUser($request));

        return new APIResponse(200, $team);
    }

    /**
     * @param Request $request
     * @return APIResponse
     */
    public function joinTeam(Request $request)
    {
        if($request->has(['team_id', 'user_id']) == false) {
            return new APIResponse(401, [], [], ["Missing 'team_id' or 'user_id' parameters."]);
        }

        $team = Team::find($request->get('team_id'));
        if($team === null) {
            return new APIResponse(404, [], [], ["Team not found."]);
        }
        $user = User::find($request->get('user_id'));
        if($user === null) {
            return new APIResponse(404, [], [], ["User not found."]);
        }

        $team->addMember($user);
        return new APIResponse(200);
    }

    public function getTeamMembers($id)
    {
        $team = Team::find($id);
        if($team === null) {
            return new APIResponse(404, [], [], ["Team not found."]);
        }

        return new APIResponse(200, $team->members);
    }

}