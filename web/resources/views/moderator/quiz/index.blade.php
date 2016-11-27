@extends('layouts.app')

@section('content')
    <br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Quiz</div>
                    <div class="panel-body">

                        <a href="{{ url('/moderator/quiz/create') }}" class="btn btn-primary color-pubuzz" title="Add New Event"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add quiz</a>
                        <br/>
                        <br/>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th> Name </th><th> Category </th><th> Description </th><th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                @foreach($quiz as $item)
                                    <tr>
                                        <td>{{ $item->name }}</td>
                                        <td style="text-align: center; color: white; background-color: {{ \App\Enum\QuizCategoryEnum::getCategoryColor($item->category) }}">{{ \App\Enum\QuizCategoryEnum::getStatusCaption($item->category) }}</td>
                                        <td>{{ $item->description }}</td>
                                        <td>
                                            <a href="{{ url('/moderator/quiz/' . $item->id) }}" class="btn color-pubuzz btn-xs" title="View Quiz"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"/></a>
                                            <a href="{{ url('/moderator/quiz/' . $item->id . '/edit') }}" class="btn color-pubuzz btn-xs" title="Edit Quiz"><span class="glyphicon glyphicon-pencil" aria-hidden="true"/></a>
                                            {!! Form::open([
                                                'method'=>'DELETE',
                                                'url' => ['/moderator/quiz', $item->id],
                                                'style' => 'display:inline'
                                            ]) !!}
                                                {!! Form::button('<span class="glyphicon glyphicon-trash" aria-hidden="true" title="Delete Quiz" />', array(
                                                        'type' => 'submit',
                                                        'class' => 'btn color-pubuzz btn-xs',
                                                        'title' => 'Delete Quiz',
                                                        'onclick'=>'return confirm("Confirm delete?")'
                                                )) !!}
                                            {!! Form::close() !!}
                                        </td>
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>
                            <div class="pagination-wrapper"> {!! $quiz->render() !!} </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection