@if(isset($questionId))
    {!! Form::hidden('question_id', $questionId) !!}
@endif
<div class="form-group {{ $errors->has('payload') ? 'has-error' : ''}}">
    {!! Form::label('payload', 'Answer/URL', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-4">
        {!! Form::input('text', 'payload', null, ['class' => 'form-control']) !!}
        {!! $errors->first('payload', '<p class="help-block">:message</p>') !!}
    </div>
    <div class="col-md-2">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>