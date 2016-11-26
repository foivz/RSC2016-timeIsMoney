package com.rsc.rschackathon.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.activities.QuestionActivity;
import com.rsc.rschackathon.api.models.Question;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentQuestionTypeTwo extends Fragment {


    private Question question;

    public FragmentQuestionTypeTwo() {

    }

    @BindView(R.id.question_text)
    TextView questionText;

    @BindView(R.id.question_image)
    ImageView questionImage;

    @BindView(R.id.button_a)
    ImageView buttonA;

    @BindView(R.id.button_b)
    ImageView buttonB;

    @BindView(R.id.button_c)
    ImageView buttonC;

    @BindView(R.id.button_d)
    ImageView buttonD;

    @BindView(R.id.button_c2)
    ImageView buttonC2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_question_type_two, container, false);

        ButterKnife.bind(this, view);

        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            question = arguments.getParcelable(QuestionActivity.QUESTION_DETAILS);
            questionText.setText(question.getText());
            if (!TextUtils.isEmpty(question.getImage())) {
                Picasso.with(getActivity()).load(question.getImage()).into(questionImage);
            }
            if (question.getAnswers().size() == 4) {
                Picasso.with(getActivity()).load(question.getAnswers().get(0).getPayload()).into(buttonA);
                Picasso.with(getActivity()).load(question.getAnswers().get(1).getPayload()).into(buttonB);
                Picasso.with(getActivity()).load(question.getAnswers().get(2).getPayload()).into(buttonC);
                Picasso.with(getActivity()).load(question.getAnswers().get(3).getPayload()).into(buttonD);
                buttonC2.setVisibility(View.GONE);

            } else if (question.getAnswers().size() == 2) {
                Picasso.with(getActivity()).load(question.getAnswers().get(1).getPayload()).into(buttonA);
                Picasso.with(getActivity()).load(question.getAnswers().get(2).getPayload()).into(buttonB);
                buttonC.setVisibility(View.GONE);
                buttonD.setVisibility(View.GONE);
                buttonC2.setVisibility(View.GONE);
            } else if (question.getAnswers().size() == 3) {
                Picasso.with(getActivity()).load(question.getAnswers().get(0).getPayload()).into(buttonA);
                Picasso.with(getActivity()).load(question.getAnswers().get(1).getPayload()).into(buttonB);
                Picasso.with(getActivity()).load(question.getAnswers().get(2).getPayload()).into(buttonC2);
                buttonC.setVisibility(View.INVISIBLE);
                buttonD.setVisibility(View.INVISIBLE);
            }

        }
        setOnClickListeners();
        return view;
    }

    private void setDisable() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonC2.setEnabled(false);
    }

    private void setOnClickListeners() {
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getAnswers().get(0).getCorrect() == 1) {
                    buttonA.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonA.setAlpha(0.6F);
                } else {
                    buttonA.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonA.setAlpha(0.6F);
                }
                setDisable();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getAnswers().get(1).getCorrect() == 1) {
                    buttonB.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonB.setAlpha(0.6F);

                } else {
                    buttonB.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonB.setAlpha(0.6F);
                }
                setDisable();

            }

        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getAnswers().get(2).getCorrect() == 1) {
                    buttonC.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonC.setAlpha(0.6F);

                } else {
                    buttonC.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonC.setAlpha(0.6F);
                }
                setDisable();

            }

        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getAnswers().get(3).getCorrect() == 1) {
                    buttonD.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonD.setAlpha(0.6F);
                } else {
                    buttonD.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonD.setAlpha(0.6F);
                }
                setDisable();

            }
        });

        buttonC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getAnswers().get(2).getCorrect() == 1) {
                    buttonC2.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonC2.setAlpha(0.6F);
                } else {
                    buttonC2.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonC2.setAlpha(0.6F);
                }
                setDisable();
            }

        });
    }
}