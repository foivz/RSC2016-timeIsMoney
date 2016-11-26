package com.rsc.rschackathon.activities;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.RecyclerViewAdapter;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.CurrentEvent;
import com.squareup.picasso.Picasso;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.Listener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 10;

    RecyclerViewAdapter recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.list_of_items)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    List<CurrentEvent.Data> currentEventList = new ArrayList<>();

    GoogleApiClient mGoogleApiClient;

    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        toolbar.setTitle("Nearby events");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);

        recyclerViewAdapter = new RecyclerViewAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter.setListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);

//        getLocation();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                try {
                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                            mGoogleApiClient);
                    if (mLastLocation != null) {
                        LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                        Log.e("LAT AND LONG123 ", String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));

                        getCurrentEvents(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    }
                } catch (SecurityException e) {
                }
                return;
        }
    }

    private void getCurrentEvents(final double latitude, final double longitude) {
        NetworkService networkService = new NetworkService();
        networkService.getAPI().getCurrentEvents().enqueue(new Callback<CurrentEvent>() {
            @Override
            public void onResponse(Call<CurrentEvent> call, Response<CurrentEvent> response) {
//                if(response.body().isSuccsess()){

                currentEventList.clear();
                currentEventList.addAll(response.body().getDataList());
                recyclerViewAdapter.setData(currentEventList, latitude, longitude);
                Log.i("DATA", "DATA JERE");
//                }
            }

            @Override
            public void onFailure(Call<CurrentEvent> call, Throwable t) {
                Log.i("ERROR", "DATA JERE");

            }
        });
    }

    @Override
    public void getDeviceAtPosition(int position) {
        showDialogEvent(currentEventList.get(position));
    }

    private void showDialogEvent(final CurrentEvent.Data event) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.event_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        CircleImageView icon = (CircleImageView) dialog.findViewById(R.id.event_image);
        TextView location = (TextView) dialog.findViewById(R.id.location);
        TextView teamMembers = (TextView) dialog.findViewById(R.id.team_members);
        TextView distance = (TextView) dialog.findViewById(R.id.distance);
        TextView startsAt = (TextView) dialog.findViewById(R.id.start_time);
        TextView description = (TextView) dialog.findViewById(R.id.description);

        CircleImageView close = (CircleImageView) dialog.findViewById(R.id.close);

        Button joinTeam = (Button) dialog.findViewById(R.id.join);
        Button createTeam = (Button) dialog.findViewById(R.id.create);


        String [] time = event.getStart_at().split(" ");

        startsAt.setText("Starts at " + time[1]);


        float[] results = new float[1];

        Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(), Double.parseDouble(event.getLat()), Double.parseDouble(event.getLng()), results);

        BigDecimal bd = new BigDecimal(results[0]).setScale(1, RoundingMode.HALF_EVEN);
        results[0] = bd.floatValue();
        if(results[0] < 1000) {
            distance.setText(results[0] + " m");
        }
        else{
            results[0] = results[0] / 1000F;
            distance.setText(results[0] + " km");

        }


        Picasso.with(getApplicationContext()).load("http://files.softicons.com/download/holidays-icons/halloween-icon-set-by-yootheme/png/512x512/pumpkin.png").into(icon);
        name.setText(event.getName());
        location.setText(event.getLocation());
        teamMembers.setText(event.getTeam_members());
        description.setText(event.getDescription());


        joinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerViewActivity.this, "JOIN", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                startActivity(new Intent(RecyclerViewActivity.this, TeamActivity.class).putExtra("id",100));

            }
        });

        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerViewActivity.this, "CREATE", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                startActivity(new Intent(RecyclerViewActivity.this, CreateTeamActivity.class));
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_pager_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(RecyclerViewActivity.this, MapsActivity.class));
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RecyclerViewActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            Log.e("LAT AND LONG123 ", String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));
            getCurrentEvents(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
