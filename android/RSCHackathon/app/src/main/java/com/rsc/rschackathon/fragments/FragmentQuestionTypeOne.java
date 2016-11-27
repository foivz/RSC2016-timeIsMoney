package com.rsc.rschackathon.fragments;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.activities.CreateTeamActivity;
import com.rsc.rschackathon.activities.MainActivity;
import com.rsc.rschackathon.activities.QuestionActivity;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.AnswerQuestionModel;
import com.rsc.rschackathon.api.models.Question;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuestionTypeOne extends Fragment {


    private Question question;

    public FragmentQuestionTypeOne() {

    }

    @BindView(R.id.question_text)
    TextView questionText;

    @BindView(R.id.question_image)
    ImageView questionImage;

    @BindView(R.id.button_a)
    Button buttonA;

    @BindView(R.id.button_b)
    Button buttonB;

    @BindView(R.id.button_c)
    Button buttonC;

    @BindView(R.id.button_d)
    Button buttonD;

    @BindView(R.id.button_c2)
    Button buttonC2;

    private NetworkService networkService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_question_type_one, container, false);

        ButterKnife.bind(this, view);

        networkService = new NetworkService();
        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            question = arguments.getParcelable(QuestionActivity.QUESTION_DETAILS);
            questionText.setText(question.getText());
            if (!TextUtils.isEmpty(question.getImage())) {
                Picasso.with(getActivity()).load(question.getImage()).into(questionImage);
            }
            if (question.getAnswers().size() == 4) {
                buttonA.setText("A. " + question.getAnswers().get(0).getPayload());
                buttonB.setText("B. " + question.getAnswers().get(1).getPayload());
                buttonC.setText("C. " + question.getAnswers().get(2).getPayload());
                buttonD.setText("D. " + question.getAnswers().get(3).getPayload());
                buttonC2.setVisibility(View.GONE);

            } else if (question.getAnswers().size() == 2) {
                buttonA.setText("A. " + question.getAnswers().get(0).getPayload());
                buttonB.setText("B. " + question.getAnswers().get(1).getPayload());
                buttonC.setVisibility(View.GONE);
                buttonD.setVisibility(View.GONE);
                buttonC2.setVisibility(View.GONE);
            } else if (question.getAnswers().size() == 3) {
                buttonA.setText("A. " + question.getAnswers().get(0).getPayload());
                buttonB.setText("B. " + question.getAnswers().get(1).getPayload());
                buttonC2.setText("C. " + question.getAnswers().get(2).getPayload());
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

    private void answerQuestion(int id) {
        Call<AnswerQuestionModel> call = networkService.getAPI()
                .answerQuestion(MainActivity.API_KEY, CreateTeamActivity.TEAM_ID, id);
        call.enqueue(new Callback<AnswerQuestionModel>() {
            @Override
            public void onResponse(Call<AnswerQuestionModel> call, Response<AnswerQuestionModel> response) {
                Log.i("TAG", "question is " + response.body().isSuccess());
            }

            @Override
            public void onFailure(Call<AnswerQuestionModel> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });

    }

    private void setOnClickListeners() {
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(question.getAnswers().get(0).getId());

                if (question.getAnswers().get(0).getCorrect() == 1) {
                    buttonA.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonA.setTextColor(getResources().getColor(R.color.white));
                } else {
                    buttonA.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonA.setTextColor(getResources().getColor(R.color.white));
                }
                setDisable();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(question.getAnswers().get(1).getId());

                if (question.getAnswers().get(1).getCorrect() == 1) {
                    buttonB.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonB.setTextColor(getResources().getColor(R.color.white));
                } else {
                    buttonB.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonB.setTextColor(getResources().getColor(R.color.white));
                }
                setDisable();

            }

        });
        buttonC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                answerQuestion(question.getAnswers().get(2).getId());
                if (question.getAnswers().get(2).getCorrect() == 1) {
                    buttonC.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonC.setTextColor(getResources().getColor(R.color.white));
                } else {
                    buttonC.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonC.setTextColor(getResources().getColor(R.color.white));
                }
                setDisable();

            }

        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(question.getAnswers().get(3).getId());
                if (question.getAnswers().get(3).getCorrect() == 1) {
                    buttonD.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonD.setTextColor(getResources().getColor(R.color.white));
                } else {
                    buttonD.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonD.setTextColor(getResources().getColor(R.color.white));
                }
                setDisable();

            }
        });

        buttonC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerQuestion(question.getAnswers().get(2).getId());
                if (question.getAnswers().get(2).getCorrect() == 1) {
                    buttonC2.setBackground(getResources().getDrawable(R.drawable.round_button_question_green));
                    buttonC2.setTextColor(getResources().getColor(R.color.white));
                } else {
                    buttonC2.setBackground(getResources().getDrawable(R.drawable.round_button_question_red));
                    buttonC2.setTextColor(getResources().getColor(R.color.white));
                }
                setDisable();
            }

        });
    }
}