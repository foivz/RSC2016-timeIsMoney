package com.rsc.rschackathon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rsc.rschackathon.R;

import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Team results");

    }
}
