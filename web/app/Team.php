<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Team extends Model
{
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'team_name', 'event_id'
    ];

    public function members()
    {
        return $this->belongsToMany('App\User', 'team_members', 'team_id', 'user_id');
    }

    public function addMember(User $user)
    {
        if(TeamMember::where('user_id', $user->id)->where('team_id', $this->id)->exists()) {
            return;
        }
        $member = new TeamMember();
        $member->user_id = $user->id;
        $member->team_id = $this->id;
        $member->save();
    }
}
