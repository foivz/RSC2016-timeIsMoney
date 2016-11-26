@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Game in progress</div>
                    <div class="panel-body">
                        <a href="{{ route('event.start', ['event_id' => $event->id]) }}" style="width: 100%" class="btn btn-success btn-lg">Next question ></a>
                        <br><br>
                        <div>
                            <h3>{{ $question->text }}</h3>
                            @if($question->image != null)
                                <img src="{{ $question->image }}" />
                            @endif
                        </div>
                        <div id="answers"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>/*
        function populateGroups()
        {
            $.ajax({
                url: "{{ route('lobby.teams', ['eventId' => $event->id]) }}",
                dataType: 'json',
                type: 'GET',
                success: function(raw) {
                    var content = "";
                    $.each(raw.data, function (key, team) {
                        var style = "";
                        if(team.members.length == {{ $event->team_members }}) {
                            style = ' style="background-color: #b4d690"';
                        }
                        content += '<div class="well"'+style+'><div style="font-size: 2em;"><b>Team name: </b>' + team.team_name + '</div>';
                        $.each(team.members, function (key, member) {
                            content += '<div style="text-align: center; display: inline-block;"><img src="' + member.avatar_url + '"/><p>'+ member.name +'</p></div>';
                        });
                        content += '</div>';
                    });
                    $('#groups').html(content);
                }
            });
        }

        $(document).ready(function() {
            setInterval(populateGroups, 500);
        });*/
    </script>
@endsection