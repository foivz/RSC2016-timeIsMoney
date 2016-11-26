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
            'location' => 'Old City Pub',
            'lat' => 46.3095798,
            'lng' => 16.337831,
            'description' => 'Have fun at Halloween quiz at Old City Pub and win some awesome prizes',
            'start_at' => \Carbon\Carbon::parse('2016-11-26 22:10'),
            'status' => \App\Enum\EventStatusEnum::STATUS_OPEN,
            'team_members' => 4,
        ]);

        \App\Event::create([
            'name' => 'Baroque Quiz',
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
            'location' => 'The Office Bar',
            'lat' => 46.3084121,
            'lng' => 16.3357846,
            'description' => 'Office is a place to be and play',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 21:30'),
            'team_members' => 4,
        ]);


        \App\Event::create([
            'name' => 'Eat, Quiz, Repeat',
            'location' => 'Restaurant Angelus',
            'lat' => 46.3070178,
            'lng' => 16.3364633,
            'description' => 'Eat awesome pizza and find out how much you know about it',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 21:45'),
            'team_members' => 4,
        ]);

        \App\Event::create([
            'name' => 'Park & Quiz',
            'location' => 'Restaurant Park',
            'lat' => 46.3070178,
            'lng' => 16.3364633,
            'description' => 'Have fun at Restaurant Park quiz',
            'start_at' => \Carbon\Carbon::parse('2016-11-27 22:10'),
            'team_members' => 4,
        ]);

    }
}
