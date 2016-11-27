@if(isset($quizId))
    {!! Form::hidden('quiz_id', $quizId) !!}
@endif
<div class="form-group {{ $errors->has('text') ? 'has-error' : ''}}">
    {!! Form::label('text', 'Text', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('text', 'text', null, ['class' => 'form-control']) !!}
        {!! $errors->first('text', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('image') ? 'has-error' : ''}}">
    {!! Form::label('image', 'Image', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('text', 'image', null, ['class' => 'form-control']) !!}
        {!! $errors->first('image', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group {{ $errors->has('type_id') ? 'has-error' : ''}}">
    {!! Form::label('type_id', 'Type Id', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::select('type_id', [1 => 'Text answers', 2 => 'Picture answers'], $question->type_id, ['class' => 'form-control']) !!}
        {!! $errors->first('type_id', '<p class="help-block">:message</p>') !!}
    </div>
</div>


<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>