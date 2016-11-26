<?php

use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        \App\User::create([
            'name' => 'Igor Rinkovec',
            'email' => 'shiqqqi@gmail.com',
            'password' => '$2y$10$NQ85KSAz4a0MBQGtuiCIdORMzsJesdHshPr0sV4ga2WogSNMssbm.',
            'avatar_url' => 'https://graph.facebook.com/v2.8/10209708626624024/picture?type=normal',
            'api_key' => 'drRF73vfZ3Md3TtmMoTHqlHbf0grUHQxKQ9dkoYe2SqbgY1CFKrV6YEKLHnzj6HF',
            'google_id' => null,
            'facebook_id' => '10209708626624024'
        ]);
    }
}