@extends('layouts.app')

@section('content')
    <br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Lobby</div>
                    <div class="panel-body">
                        <a href="{{ route('event.start', ['event_id' => $event->id]) }}" style="width: 100%" class="btn btn-success btn-lg">Start quiz</a>
                        <br><br>
                        <div id="groups"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
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
                        content += '<div class="well lobby-user"'+style+'><div style="font-size: 2em;"><b>Team name: </b>' + team.team_name + '</div>';
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
            setInterval(populateGroups, 1000);
        });
    </script>
@endsection