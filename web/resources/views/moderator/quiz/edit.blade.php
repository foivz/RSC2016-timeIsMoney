@extends('layouts.app')

@section('content')
    <br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Edit Quiz {{ $quiz->id }}</div>
                    <div class="panel-body">

                        @if ($errors->any())
                            <ul class="alert alert-danger">
                                @foreach ($errors->all() as $error)
                                    <li>{{ $error }}</li>
                                @endforeach
                            </ul>
                        @endif

                        {!! Form::model($quiz, [
                            'method' => 'PATCH',
                            'url' => ['/moderator/quiz', $quiz->id],
                            'class' => 'form-horizontal',
                            'files' => true
                        ]) !!}

                        @include ('moderator.quiz.form', ['submitButtonText' => 'Update'])

                        {!! Form::close() !!}

                    </div>
                </div>
                <h2>Questions</h2>
            </div>
        </div>
        @foreach($questions as $question)
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Edit Question {{ $question->id }}</div>
                    <div class="panel-body">
                        {!! Form::model($question, [
                            'method' => 'PATCH',
                            'url' => ['/moderator/question', $question->id],
                            'class' => 'form-horizontal',
                            'files' => true
                        ]) !!}

                        @include ('moderator.question.form', ['submitButtonText' => 'Update'])

                        <h3>Answers</h3>
                        @foreach($question->answers as $answer)
                        {!! Form::model($answer, [
                            'method' => 'PATCH',
                            'url' => ['/moderator/answer', $answer->id],
                            'class' => 'form-horizontal',
                            'files' => true
                        ]) !!}

                        @include ('moderator.answer.form', ['submitButtonText' => 'Update'])

                        {!! Form::close() !!}
                        @endforeach
                        {!! Form::model(new \App\Answer(), [
                            'method' => 'POST',
                            'url' => ['/moderator/answer'],
                            'class' => 'form-horizontal',
                            'files' => true
                        ]) !!}

                        @include ('moderator.answer.form', ['submitButtonText' => 'Create', 'questionId' => $question->id])

                        {!! Form::close() !!}

                        {!! Form::close() !!}
                    </div>
                </div>
            </div>
        </div>
        @endforeach
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Create a question</div>
                    <div class="panel-body">
                        {!! Form::model(new \App\Question(), [
                        'method' => 'POST',
                        'url' => ['/moderator/question'],
                        'class' => 'form-horizontal',
                        'files' => true
                        ]) !!}

                        @include ('moderator.question.form', ['submitButtonText' => 'Create', 'quizId' => $quiz->id])
                        {!! Form::close() !!}
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection