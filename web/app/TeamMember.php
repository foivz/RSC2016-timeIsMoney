<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TeamMember extends Model
{
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'team_id', 'user_id', 'score', 'tiebreaker_score'
    ];

   public function user() {
       return $this->hasOne('App\User');
   }
}
