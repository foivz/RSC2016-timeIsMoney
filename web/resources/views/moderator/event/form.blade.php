<div class="form-group {{ $errors->has('status') ? 'has-error' : ''}}">
    {!! Form::label('status', 'Status', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::select('status', [    0 => 'Unpublished',
    1 => 'Scheduled',
    2 => 'Open',
    3 => 'Closed',
    4 => 'Started',
    5 => 'Finished',
], $event->status, ['class' => 'form-control']) !!}
        {!! $errors->first('status', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('name') ? 'has-error' : ''}}">
    {!! Form::label('name', 'Name', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('text', 'name', null, ['class' => 'form-control']) !!}
        {!! $errors->first('name', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('description') ? 'has-error' : ''}}">
    {!! Form::label('description', 'Description', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::textarea('description', null, ['class' => 'form-control']) !!}
        {!! $errors->first('description', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('location') ? 'has-error' : ''}}">
    {!! Form::label('location', 'Location', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('text', 'location', null, ['class' => 'form-control']) !!}
        {!! $errors->first('location', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('lat') ? 'has-error' : ''}}">
    {!! Form::label('lat', 'Lat', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::number('lat', null, ['class' => 'form-control']) !!}
        {!! $errors->first('lat', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('lng') ? 'has-error' : ''}}">
    {!! Form::label('lng', 'Lng', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::number('lng', null, ['class' => 'form-control']) !!}
        {!! $errors->first('lng', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('team_members') ? 'has-error' : ''}}">
    {!! Form::label('team_members', 'Team Members', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::number('team_members', null, ['class' => 'form-control']) !!}
        {!! $errors->first('team_members', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('start_at') ? 'has-error' : ''}}">
    {!! Form::label('start_at', 'Start At', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('datetime-local', 'start_at', null, ['class' => 'form-control']) !!}
        {!! $errors->first('start_at', '<p class="help-block">:message</p>') !!}
    </div>
</div>


<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>