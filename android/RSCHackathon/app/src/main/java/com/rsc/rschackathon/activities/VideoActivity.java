package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    public static Intent createIntent(final Context context) {
        return new Intent(context, VideoActivity.class);
    }

    @BindView(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.finish_video);
        videoView.setVideoURI(video);
        videoView.start();
    }
}
