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
            'image' => "https://www.google.hr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiundqs78bQAhXKAxoKHYLpARQQjRwIBw&url=http%3A%2F%2Fdraculaevolution.weebly.com%2Fdracula.html&psig=AFQjCNEK1x3YO9lRJ7CfOplGmFKdJd0aUw&ust=1480265085573442",
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
