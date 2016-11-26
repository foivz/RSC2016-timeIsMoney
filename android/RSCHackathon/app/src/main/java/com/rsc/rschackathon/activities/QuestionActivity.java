package com.rsc.rschackathon.activities;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.Question;
import com.rsc.rschackathon.fragments.FragmentQuestionTypeOne;

import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    public static final String QUESTION_DETAILS = "QUESTION_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Quiz name will go here");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //TODO HERE HANDLE FRAGMENT CHANGE FOR EVERY TYPE OF QUESTION


        int q = 1;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (q == 1) {
            FragmentQuestionTypeOne questionTypeOne = new FragmentQuestionTypeOne();
            final Bundle bundle = new Bundle();
            Question question = new Question("PITANEJJEJEJE");
            bundle.putParcelable(QUESTION_DETAILS, question);
            questionTypeOne.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, questionTypeOne).commit();
        }
        if (q == 2) {

        }
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
