<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Event extends Model
{

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'address', 'lat', 'lng', 'description',
    ];

    public function teams()
    {
        return $this->hasMany('App\Team');
    }

    public function quiz()
    {
        return $this->belongsTo('App\Quiz');
    }

}
