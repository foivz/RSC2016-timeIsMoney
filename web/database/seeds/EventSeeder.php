<?php

use Illuminate\Database\Seeder;

class EventSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        \App\Event::create([
            'name' => 'Halloween Quiz',
            'quiz_id' => 1,
            'location' => 'Old City Pub',
            'lat' => 46.3095798,
            'lng' => 16.337831,
            'description' => 'Have fun at Halloween quiz at Old City Pub and win some awesome prizes.

            1st place: 12L of beer
            2nd place: 6L of beer
            3rd place: 3L of beer',
            'start_at' => \Carbon\Carbon::parse('2016-11-26 22:10'),
            'status' => \App\Enum\EventStatusEnum::STATUS_OPEN,
            'team_members' => 2,
        ]);

        \App\Event::create([
            'name' => 'Baroque Quiz',
            'quiz_id' => 1,
            'location' => 'Ritz Cafe Bar',
            'lat' => 46.3080559,
            'lng' => 16.3352782,
            'description' => 'Dress up barouqe style and visit us to play!',
            'start_at' => \Carbon\Carbon::parse('2016-11-26 23:15'),
            'status' => \App\Enum\EventStatusEnum::STATUS_OPEN,
            'team_members' => 4,
        ]);

        \App\Event::create([
            'name' => 'Office Quiz',
            'quiz_id' => 1,
            'location' => 'The Office Bar',
            'lat' => 46.3084121,
            'lng' => 16.3357846,
            'description' => 'Office is a place to be and play',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 21:30'),
            'team_members' => 4,
        ]);


        \App\Event::create([
            'name' => 'Eat, Quiz, Repeat',
            'quiz_id' => 1,
            'location' => 'Restaurant Angelus',
            'lat' => 46.3070178,
            'lng' => 16.3364633,
            'description' => 'Eat awesome pizza and find out how much you know about it',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 21:45'),
            'team_members' => 4,
        ]);

        \App\Event::create([
            'name' => 'Park & Quiz',
            'quiz_id' => 1,
            'location' => 'Restaurant Park',
            'lat' => 46.3077495,
            'lng' => 16.3405937,
            'description' => 'Have fun at Restaurant Park quiz',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 22:10'),
            'team_members' => 4,
        ]);

    }
}
