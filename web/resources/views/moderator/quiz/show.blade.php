@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Quiz {{ $quiz->id }}</div>
                    <div class="panel-body">

                        <a href="{{ url('moderator/quiz/' . $quiz->id . '/edit') }}" class="btn btn-primary btn-xs" title="Edit Quiz"><span class="glyphicon glyphicon-pencil" aria-hidden="true"/></a>
                        {!! Form::open([
                            'method'=>'DELETE',
                            'url' => ['moderator/quiz', $quiz->id],
                            'style' => 'display:inline'
                        ]) !!}
                            {!! Form::button('<span class="glyphicon glyphicon-trash" aria-hidden="true"/>', array(
                                    'type' => 'submit',
                                    'class' => 'btn btn-danger btn-xs',
                                    'title' => 'Delete Quiz',
                                    'onclick'=>'return confirm("Confirm delete?")'
                            ))!!}
                        {!! Form::close() !!}
                        <br/>
                        <br/>

                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <th>ID</th><td>{{ $quiz->id }}</td>
                                    </tr>
                                    <tr><th> Name </th><td> {{ $quiz->name }} </td></tr><tr><th> Category </th><td> {{ $quiz->category }} </td></tr><tr><th> Description </th><td> {{ $quiz->description }} </td></tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection