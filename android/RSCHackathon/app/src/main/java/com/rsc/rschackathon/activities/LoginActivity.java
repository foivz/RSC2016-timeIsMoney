package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.ViewPagerAdapter;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.DocsApi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private NetworkService networkService;

    public static Intent createIntent(final Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_activity_login_button)
    public void loginClicked() {
        startActivity(new Intent(LoginActivity.this, RecyclerViewActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        networkService = new NetworkService();
        Call<List<DocsApi>> call = networkService.getAPI().getRepositories();
        call.enqueue(new Callback<List<DocsApi>>() {
            @Override
            public void onResponse(Call<List<DocsApi>> call, Response<List<DocsApi>> response) {
                Log.i("TAG", response.body().get(0).title);
            }

            @Override
            public void onFailure(Call<List<DocsApi>> call, Throwable t) {
                Log.i("TAG", "fail");
            }
        });*/
    }
}
