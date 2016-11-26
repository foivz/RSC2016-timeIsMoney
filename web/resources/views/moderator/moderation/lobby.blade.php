@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Lobby</div>
                    <div class="panel-body">
                        <a href="#" style="width: 100%" class="btn btn-success btn-lg">Start quiz</a>
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
                        content += '<div class="well"><div style="font-size: 2em;"><b>Team name: </b>' + team.team_name + '</div>';
                        $.each(team.members, function (key, member) {
                            content += '<div style="text-align: center"><img src="' + member.avatar_url + '"/><p>'+ member.name +'</p></div>';
                        });
                        content += '</div>';
                    });
                    $('#groups').html(content);
                }
            });
        }

        $(document).ready(function() {
            setInterval(populateGroups, 500);
        });
    </script>
@endsection