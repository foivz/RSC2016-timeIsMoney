<?php

namespace App\Http\Controllers\Moderator;

use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Question;
use App\Quiz;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Session;

class QuizController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        $quiz = Quiz::paginate(25);

        return view('moderator.quiz.index', compact('quiz'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('moderator.quiz.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(Request $request)
    {
        
        $requestData = $request->all();
        
        $quiz = Quiz::create($requestData);
        $quiz->user_id = Auth::user()->id;
        $quiz->save();

        Session::flash('flash_message', 'Quiz added!');

        return redirect('moderator/quiz');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     *
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $quiz = Quiz::findOrFail($id);

        return view('moderator.quiz.show', compact('quiz'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     *
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $quiz = Quiz::findOrFail($id);
        $questions = Question::where('quiz_id', $quiz->id)->get();

        return view('moderator.quiz.edit', compact('quiz'))->with('questions', $questions);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int  $id
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, Request $request)
    {
        
        $requestData = $request->all();
        
        $quiz = Quiz::findOrFail($id);
        $quiz->update($requestData);

        Session::flash('flash_message', 'Quiz updated!');

        return redirect('moderator/quiz');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        Quiz::destroy($id);

        Session::flash('flash_message', 'Quiz deleted!');

        return redirect('moderator/quiz');
    }
}
