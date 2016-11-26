@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Question</div>
                    <div class="panel-body">

                        <a href="{{ url('/moderator/question/create') }}" class="btn btn-primary btn-xs" title="Add New Question"><span class="glyphicon glyphicon-plus" aria-hidden="true"/></a>
                        <br/>
                        <br/>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th>ID</th><th> Quiz Id </th><th> Text </th><th> Image </th><th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                @foreach($question as $item)
                                    <tr>
                                        <td>{{ $item->id }}</td>
                                        <td>{{ $item->quiz_id }}</td><td>{{ $item->text }}</td><td>{{ $item->image }}</td>
                                        <td>
                                            <a href="{{ url('/moderator/question/' . $item->id) }}" class="btn btn-success btn-xs" title="View Question"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"/></a>
                                            <a href="{{ url('/moderator/question/' . $item->id . '/edit') }}" class="btn btn-primary btn-xs" title="Edit Question"><span class="glyphicon glyphicon-pencil" aria-hidden="true"/></a>
                                            {!! Form::open([
                                                'method'=>'DELETE',
                                                'url' => ['/moderator/question', $item->id],
                                                'style' => 'display:inline'
                                            ]) !!}
                                                {!! Form::button('<span class="glyphicon glyphicon-trash" aria-hidden="true" title="Delete Question" />', array(
                                                        'type' => 'submit',
                                                        'class' => 'btn btn-danger btn-xs',
                                                        'title' => 'Delete Question',
                                                        'onclick'=>'return confirm("Confirm delete?")'
                                                )) !!}
                                            {!! Form::close() !!}
                                        </td>
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>
                            <div class="pagination-wrapper"> {!! $question->render() !!} </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection