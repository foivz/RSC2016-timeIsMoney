@extends('layouts.app')

@section('content')
    <br><br>
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
                                @foreach($event as $item)
                                    <tr>
                                        <td><b>{{ $item->name }}</b></td>
                                        <td>
                                            <a href="{{ route('lobby', ['eventId' => $item->id]) }}" class="btn btn-success btn" title="View Quiz"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Lobby</a>
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