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
            'text' => 'Which of these vegetables is used as decoration on halloween?',
            'image' => null,
            'type_id' => 2
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://steamykitchen.com/wp-content/uploads/2010/09/baked-spaghetti-squash-garlic-butter-4564.jpg',
            'correct' => true,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://www.thealthbenefitsof.com/wp-content/uploads/2015/08/Health-Benefits-of-Paprika.jpg',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://ipravda.sk/res/2013/05/20/thumbs/mrkva-karotka_01-nestandard2.jpg',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://weknowyourdreams.com/images/potato/potato-01.jpg',
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

        $question = \App\Question::create([
            'quiz_id' => $quiz->id,
            'text' => 'Which of these is an acorn pumpkin?',
            'image' => null,
            'type_id' => 2
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://steamykitchen.com/wp-content/uploads/2010/09/baked-spaghetti-squash-garlic-butter-4564.jpg',
            'correct' => true,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://farm5.static.flickr.com/4087/5196852790_3700682351.jpg',
            'correct' => false,
        ]);
        \App\Answer::create([
            'question_id' => $question->id,
            'payload' => 'http://dingo.care2.com/pictures/greenliving/uploads/2015/09/ThinkstockPhotos-467072711-293x4431.jpg',
            'correct' => false,
        ]);

    }
}
