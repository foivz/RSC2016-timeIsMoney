package com.rsc.rschackathon.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.rsc.rschackathon.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoolapsingToolbarActivity extends AppCompatActivity {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @BindView(R.id.about_this_app_image)
    ImageView imageView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coolapsing_toolbar);

        ButterKnife.bind(this);

        collapsingToolbarLayout.setTitle("ABOUT THIS APP");
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.red));

        setupActionBar();
        Picasso.with(CoolapsingToolbarActivity.this).load("https://files.graphiq.com/stories/t4/15_Tiniest_Dog_Breeds_1718_3083.jpg").into(imageView);

    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
