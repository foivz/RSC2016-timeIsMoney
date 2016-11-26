@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">Login</div>
                <div class="panel-body">
                    <div class="text-center">
                        <a href="{{ route('socialAuth', ['provider' => 'facebook']) }}" class="btn btn-default">
                            <i class="fa fa-facebook"></i>
                            Prijava s Facebook računom
                        </a>

                        <a href="{{ route('socialAuth', ['provider' => 'google']) }}" class="btn btn-default">
                            <i class="fa fa-google"></i>
                            Prijava s Google računom
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
