<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Quiz extends Model
{
    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'quizzes';

    /**
    * The database primary key value.
    *
    * @var string
    */
    protected $primaryKey = 'id';

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['name', 'category', 'description', 'user_id'];

    public function questions()
    {
        return $this->hasMany('App\Question');
    }

    
}
