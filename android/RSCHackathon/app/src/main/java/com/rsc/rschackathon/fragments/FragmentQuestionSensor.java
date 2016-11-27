package com.rsc.rschackathon.fragments;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.activities.CreateTeamActivity;
import com.rsc.rschackathon.activities.MainActivity;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.AnswerQuestionModel;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuestionSensor extends Fragment implements SensorEventListener {

    private SensorManager senSensorManager;

    private Sensor senAccelerometer;

    private long lastUpdate = 0;

    private float last_x, last_y, last_z;

    CountDownTimer viewCounter;

    MediaPlayer mPlayer;

    private float totalShake = 0;

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @BindView(R.id.text_shake)
    TextView shakeNow;

    @BindView(R.id.intro_text)
    TextView introText;

    @BindView(R.id.phone)
    ImageView phoneImage;

    @BindView(R.id.begin_text)
    TextView beginText;


    public FragmentQuestionSensor() {
    }

    private void time() {
        if (viewCounter != null) {
            viewCounter.cancel();
            viewCounter = null;
        }
        viewCounter = new CountDownTimer(5000, 20) {

            @Override
            public void onTick(long l) {
                shakeNow.setText(String.format("%02d:%02d:%d",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)),
                        TimeUnit.MILLISECONDS.toMillis(l)));
            }

            @Override
            public void onFinish() {
                shakeNow.setVisibility(View.GONE);
                introText.setVisibility(View.GONE);
                senSensorManager.registerListener(FragmentQuestionSensor.this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                mPlayer = MediaPlayer.create(getActivity(), R.raw.shake);
                mPlayer.start();
                timeShake();
            }
        }.start();
    }

    private void timeShake() {
        if (viewCounter != null) {
            viewCounter.cancel();
            viewCounter = null;
        }
        viewCounter = new CountDownTimer(4000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //TODO SEND TO SERVER
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
                shakeNow.setVisibility(View.VISIBLE);
                introText.setVisibility(View.VISIBLE);
                senSensorManager.unregisterListener(FragmentQuestionSensor.this);
                introText.setText("Your result is");
                shakeNow.setText(String.valueOf(totalShake));

                NetworkService networkService = new NetworkService();
                Call<AnswerQuestionModel> call = networkService.getAPI()
                        .tieBreaker(MainActivity.API_KEY, CreateTeamActivity.TEAM_ID, Math.round(totalShake));
                call.enqueue(new Callback<AnswerQuestionModel>() {
                    @Override
                    public void onResponse(Call<AnswerQuestionModel> call, Response<AnswerQuestionModel> response) {
                        if (response.body() != null) {
                            Log.i("TAG", "slanje tiebreakera uspjesno " + response.body().isSuccess());
                        }
                    }

                    @Override
                    public void onFailure(Call<AnswerQuestionModel> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                    }
                });

            }
        }.start();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_question_sensor, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        senSensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                beginText.setVisibility(View.GONE);
                phoneImage.setVisibility(View.VISIBLE);
                shakeNow.setVisibility(View.VISIBLE);
                introText.setVisibility(View.VISIBLE);
                time();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                last_x = x;
                last_y = y;
                last_z = z;

                totalShake += Math.abs(x) + Math.abs(y) + Math.abs(z);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }
}
