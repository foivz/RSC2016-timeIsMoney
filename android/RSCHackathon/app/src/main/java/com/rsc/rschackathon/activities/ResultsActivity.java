package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.ResultsAdapter;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.TeamResult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends AppCompatActivity {

    ResultsAdapter resultsAdapter;

    LinearLayoutManager linearLayoutManager;

    public static Intent createIntent(final Context context) {
        return new Intent(context, ResultsActivity.class);
    }

    @BindView(R.id.list_of_results)
    RecyclerView listOfResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsAdapter = new ResultsAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        ButterKnife.bind(this);

        listOfResults.setLayoutManager(linearLayoutManager);
        listOfResults.setAdapter(resultsAdapter);

        getSupportActionBar().setTitle("Team results");

        NetworkService networkService = new NetworkService();
        Call<TeamResult> call = networkService.getAPI().getResults(RecyclerViewActivity.QUIZZ_ID);
        call.enqueue(new Callback<TeamResult>() {
            @Override
            public void onResponse(Call<TeamResult> call, Response<TeamResult> response) {
                if (response.body() != null) {
                    resultsAdapter.setData(response.body().getData(), ResultsActivity.this);
                }
            }

            @Override
            public void onFailure(Call<TeamResult> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });

    }

    @OnClick(R.id.back_to_homepage)
    public void goBackToShadow() {
        startActivity(RecyclerViewActivity.createIntent(this));
    }
}
