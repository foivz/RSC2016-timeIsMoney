@extends('layouts.app')

@section('content')
    <br><br>
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
                        <div>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Team name</th>
                                        <th>Score</th>
                                    </tr>
                                </thead>
                                <tbody id="scoreboard">

                                </tbody>
                            </table>
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
                url: "{{ route('event.score', ['eventId' => $event->id]) }}",
                dataType: 'json',
                type: 'GET',
                success: function(raw) {
                    var content = "";
                    $.each(raw.data, function (key, team) {
                        content += '<tr><td>'+(key+1)+'</td><td>'+team.team_name+'</td><td>'+team.score_sum+'</td></tr>';
                        console.log(content);
                    });
                    console.log(content);
                    $('#scoreboard').html(content);
                }
            });
        }

        $(document).ready(function() {
            setInterval(populateScoreboard, 1000);
        });
    </script>
@endsection