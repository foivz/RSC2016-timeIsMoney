package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.CreateTeamApi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTeamActivity extends AppCompatActivity {

    public static int TEAM_ID;

    public static Intent createIntent(final Context context) {
        return new Intent(context, CreateTeamActivity.class);
    }

    @BindView(R.id.team_name_edit_text)
    EditText teamNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Create new team");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @OnClick(R.id.create_team_button)
    public void createTeam() {

        //TODO TINOS CODE UNCOMMENCT AFTER
//        NetworkService networkService = new NetworkService();
//        Call<CreateTeamApi> call = networkService.getAPI().createTeam(MainActivity.API_KEY, 1, teamNameEditText.getText().toString());
//        call.enqueue(new Callback<CreateTeamApi>() {
//            @Override
//            public void onResponse(Call<CreateTeamApi> call, Response<CreateTeamApi> response) {
//                Log.i("TAG", "response");
//                if (response.isSuccessful()) {
//                    Log.i("TAG", response.message());
//                    if (response.body().isSuccess()) {
//                        TEAM_ID = response.body().getData().getId();
//                        startActivity(ServerTeamActivity.createIntent(CreateTeamActivity.this));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CreateTeamApi> call, Throwable t) {
//                Log.i("TAG", t.getMessage());
//            }
//        });

        startActivity(new Intent(CreateTeamActivity.this, QuestionActivity.class));

    }
}
