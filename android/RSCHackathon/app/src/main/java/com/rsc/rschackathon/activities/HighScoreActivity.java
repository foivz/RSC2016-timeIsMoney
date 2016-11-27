package com.rsc.rschackathon.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.HighScoreAdapter;
import com.rsc.rschackathon.adapters.RecyclerViewAdapter;
import com.rsc.rschackathon.api.models.HighScore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighScoreActivity extends AppCompatActivity {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @BindView(R.id.about_this_app_image)
    ImageView imageView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.list_of_items)
    RecyclerView recyclerView;


    HighScoreAdapter recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    List<HighScore> highScoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        ButterKnife.bind(this);

        collapsingToolbarLayout.setTitle(" ");
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        setupActionBar();
        Picasso.with(HighScoreActivity.this).load(R.drawable.highscore).into(imageView);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("High score");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        recyclerViewAdapter = new HighScoreAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        generateData();

    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void generateData(){

        highScoreList.clear();
        highScoreList.add(new HighScore(4212, "Time is Money"));
        highScoreList.add(new HighScore(4023, "Power Rangers"));
        highScoreList.add(new HighScore(3982, "Wild Boys Team"));
        highScoreList.add(new HighScore(3782, "Team Bears"));
        highScoreList.add(new HighScore(3755, "Baby Boys"));
        highScoreList.add(new HighScore(3701, "Team 123"));
        highScoreList.add(new HighScore(3645, "Wii Not Fit"));
        highScoreList.add(new HighScore(3489, "Team 100"));
        highScoreList.add(new HighScore(3213, "Blabla"));
        highScoreList.add(new HighScore(3134, "Ez"));
        highScoreList.add(new HighScore(3103, "We are here for Beer"));

        recyclerViewAdapter.setData(highScoreList, this);


    }
}
