<?php

namespace App\Http\Controllers\Moderator;

use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Answer;
use Illuminate\Http\Request;
use Session;

class AnswerController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        $answer = Answer::paginate(25);

        return view('moderator.answer.index', compact('answer'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('moderator.answer.create');
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
        
        Answer::create($requestData);

        Session::flash('flash_message', 'Answer added!');

        return redirect('moderator/answer');
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
        $answer = Answer::findOrFail($id);

        return view('moderator.answer.show', compact('answer'));
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
        $answer = Answer::findOrFail($id);

        return view('moderator.answer.edit', compact('answer'));
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
        
        $answer = Answer::findOrFail($id);
        $answer->update($requestData);

        Session::flash('flash_message', 'Answer updated!');

        return redirect('moderator/answer');
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
        Answer::destroy($id);

        Session::flash('flash_message', 'Answer deleted!');

        return redirect('moderator/answer');
    }
}
