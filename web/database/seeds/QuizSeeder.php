<?php

use Illuminate\Database\Seeder;

class QuizSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $quiz = \App\Quiz::create([
            'name' => 'Halloween Trivia',
            'category' => \App\Enum\QuizCategoryEnum::QUIZ_CULTURE,
            'description' => 'Set of questions perfect for Halloween parties.',
            'user_id' => 1
        ]);

        $question = \App\Question::create([
            'quiz_id' => $quiz->id,
            'text' => 'In which country did Halloween originate?',
            'image' => null,
            'type_id' => 1
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'USA',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Ireland',
            'correct' => true,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Croatia',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Romania',
            'correct' => false,
        ]);

        $question = \App\Question::create([
            'quiz_id' => $quiz->id,
            'text' => 'Which Catholic Church holiday is Halloween linked to?',
            'image' => null,
            'type_id' => 1
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Christmas',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Easter',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'All Saints (Hallows) Day',
            'correct' => true,
        ]);

        $question = \App\Question::create([
            'quiz_id' => $quiz->id,
            'text' => 'Who is the person depicted on the picture?',
            'image' => "http://draculaevolution.weebly.com/uploads/6/6/2/0/662002/7060563_orig.jpg",
            'type_id' => 1
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Vlad the Impaler',
            'correct' => true,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Mark Zuckerberg',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'Donald Trump',
            'correct' => false,
        ]);

    }
}
