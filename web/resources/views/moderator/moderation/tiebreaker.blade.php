@extends('layouts.app')

@section('content')
    <br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Tiebreaker in progress</div>
                    <div class="panel-body">
                        <a href="{{ route('event.end', ['event_id' => $event->id]) }}" style="width: 100%" class="btn btn-success btn-lg">End game</a>
                        <br><br>
                        <div class="row text-center">
                            <div class="col-md-6">
                                <div class="well" id="team1">

                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="well" id="team2">

                                </div>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <img src="{{ asset('shake.png') }}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function populateScoreboard()
        {
            $.ajax({
                url: "{{ route('event.tiebreaker.score', ['eventId' => $event->id]) }}",
                dataType: 'json',
                type: 'GET',
                success: function(raw) {
                    $("#team1").html("<h2>"+raw.data[0].team_name+"</h2><h3>"+raw.data[0].tiebreaker_score_sum+"</h3>");
                    $("#team2").html("<h2>"+raw.data[1].team_name+"</h2><h3>"+raw.data[1].tiebreaker_score_sum+"</h3>");
                }
            });
        }

        $(document).ready(function() {
            setInterval(populateScoreboard, 1000);
        });
    </script>
@endsection