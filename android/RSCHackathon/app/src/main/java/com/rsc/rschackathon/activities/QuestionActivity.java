package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.Question;
import com.rsc.rschackathon.api.models.QustionAPIModel;
import com.rsc.rschackathon.fragments.FragmentQuestionTypeOne;
import com.rsc.rschackathon.fragments.FragmentQuestionTypeTwo;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    public static final String QUESTION_DETAILS = "QUESTION_DETAILS";

    Thread thread;

    Handler handler;

    private static int typeId = 0;

    NetworkService networkService;

    @BindView(R.id.waiting_text)
    TextView waitingText;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();
        getSupportActionBar().setTitle(RecyclerViewActivity.QUIZ_NAME);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        networkService = new NetworkService();
        handler = new Handler();

        thread = new Thread() {
            public void run() {

                Call<QustionAPIModel> call = networkService.getAPI().getQuestions(RecyclerViewActivity.QUIZZ_ID, MainActivity.API_KEY);
                call.enqueue(new Callback<QustionAPIModel>() {
                    @Override
                    public void onResponse(Call<QustionAPIModel> call, Response<QustionAPIModel> response) {
                        if (response.body() != null) {
                            if (response.body().getMessages().isEmpty()) {

                                waitingText.setVisibility(View.GONE);

                                if (typeId != (response.body().getData().getId())) {
                                    typeId = (response.body().getData().getId());
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    if (Integer.valueOf(response.body().getData().getType_id()) == 1) {
                                        FragmentQuestionTypeOne questionTypeOne = new FragmentQuestionTypeOne();
                                        final Bundle bundle = new Bundle();
                                        List<Question.Answers> answerses = new ArrayList<Question.Answers>();
                                        for (int i = 0; i < response.body().getData().getAnswers().size(); i++) {
                                            Question.Answers answers = new Question.Answers(
                                                    response.body().getData().getAnswers().get(i).getId(),
                                                    Integer.valueOf(response.body().getData().getAnswers().get(i).getQuestion_id()),
                                                    response.body().getData().getAnswers().get(i).getPayload(),
                                                    Integer.valueOf(response.body().getData().getAnswers().get(i).getCorrect()));
                                            answerses.add(answers);

                                        }
                                        Question question = new Question(response.body().getData().getId(),
                                                Integer.valueOf(response.body().getData().getQuiz_id()),
                                                response.body().getData().getText(), response.body().getData().getImage(), typeId,
                                                answerses);
                                        bundle.putParcelable(QUESTION_DETAILS, question);
                                        questionTypeOne.setArguments(bundle);
                                        fragmentTransaction.replace(R.id.fragment_container, questionTypeOne).commit();
                                    } else if (Integer.valueOf(response.body().getData().getType_id()) == 2) {
                                        FragmentQuestionTypeTwo questionTypeTwo = new FragmentQuestionTypeTwo();
                                        final Bundle bundle = new Bundle();
                                        List<Question.Answers> answerses = new ArrayList<Question.Answers>();
                                        for (int i = 0; i < response.body().getData().getAnswers().size(); i++) {
                                            Question.Answers answers = new Question.Answers(
                                                    response.body().getData().getAnswers().get(i).getId(),
                                                    Integer.valueOf(response.body().getData().getAnswers().get(i).getQuestion_id()),
                                                    response.body().getData().getAnswers().get(i).getPayload(),
                                                    Integer.valueOf(response.body().getData().getAnswers().get(i).getCorrect()));
                                            answerses.add(answers);

                                        }
                                        Question question = new Question(response.body().getData().getId(),
                                                Integer.valueOf(response.body().getData().getQuiz_id()),
                                                response.body().getData().getText(), response.body().getData().getImage(), typeId,
                                                answerses);
                                        bundle.putParcelable(QUESTION_DETAILS, question);
                                        questionTypeTwo.setArguments(bundle);
                                        fragmentTransaction.replace(R.id.fragment_container, questionTypeTwo).commit();
                                    }
                                }

                            } else {
                                if (response.body().getMessages().get(0).equals("Quiz is finished.")) {
                                    handler.removeCallbacks(thread);
                                    try {
                                        thread.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            Log.i("TAG", "response");
                        }
                    }

                    @Override
                    public void onFailure(Call<QustionAPIModel> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                    }
                });
                handler.postDelayed(this, 1000);
            }
        };

        handler.removeCallbacks(thread);
        handler.postDelayed(thread, 0);

        //getQuestions();
    }

    private void getQuestions() {
        NetworkService networkService = new NetworkService();
        networkService.getAPI().getQuestion().enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                //TODO HERE HANDLE FRAGMENT CHANGE FOR EVERY TYPE OF QUESTION

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (response.body().getType_id() == 1) {
                    FragmentQuestionTypeOne questionTypeOne = new FragmentQuestionTypeOne();
                    final Bundle bundle = new Bundle();
                    Question question = response.body();
                    bundle.putParcelable(QUESTION_DETAILS, question);
                    questionTypeOne.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, questionTypeOne).commit();
                } else if (response.body().getType_id() == 2) {
                    FragmentQuestionTypeTwo questionTypeTwo = new FragmentQuestionTypeTwo();
                    final Bundle bundle = new Bundle();
                    Question question = response.body();
                    bundle.putParcelable(QUESTION_DETAILS, question);
                    questionTypeTwo.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, questionTypeTwo).commit();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        showDialogEvent();
    }

    private void showDialogEvent() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.leave_quizz_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button yes = (Button) dialog.findViewById(R.id.yes);
        Button no = (Button) dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuestionActivity.this, RecyclerViewActivity.class));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
