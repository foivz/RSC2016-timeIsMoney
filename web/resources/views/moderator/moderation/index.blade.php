@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Current events</div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th> Name </th><th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                @foreach($quiz as $item)
                                    <tr>
                                        <td><b>{{ $item->name }}</b></td>
                                        <td>
                                            <a href="{{ url('/moderator/quiz/' . $item->id) }}" class="btn btn-success btn" title="View Quiz"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Moderate</a>
                                        </td>
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection