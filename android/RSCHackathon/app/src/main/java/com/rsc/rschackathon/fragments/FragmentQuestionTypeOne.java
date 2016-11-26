package com.rsc.rschackathon.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.activities.QuestionActivity;
import com.rsc.rschackathon.api.models.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentQuestionTypeOne extends Fragment {


    private Question question;

    public FragmentQuestionTypeOne() {

    }

    @BindView(R.id.question_text)
    TextView questionText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_question_type_one, container, false);

        ButterKnife.bind(this, view);

        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            question = arguments.getParcelable(QuestionActivity.QUESTION_DETAILS);
            questionText.setText(question.getText());
        }

        return view;
    }
}