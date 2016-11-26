<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateEventsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('events', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('quiz_id');
            $table->tinyInteger('status')->default(0);
            $table->text('name');
            $table->text('description');
            $table->text('location');
            $table->decimal('lat',10,7);
            $table->decimal('lng',10,7);
            $table->integer('team_members');
            $table->dateTime('start_at');
            $table->integer('current_question')->default(0);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('events');
    }
}
