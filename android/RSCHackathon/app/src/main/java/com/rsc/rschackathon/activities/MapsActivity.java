package com.rsc.rschackathon.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.CurrentEvent;
import com.squareup.picasso.Picasso;

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

public class MapsActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 10;

    MapView mMapView;
    public static GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;

    Location mLastLocation;

    List<CurrentEvent.Data> dataList = new ArrayList<>();

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        toolbar.setTitle("Events");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mMapView = (MapView) findViewById(R.id.gmap);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        try {
            MapsInitializer.initialize(getApplicationContext().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MapsActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                } else {
                    googleMap.setMyLocationEnabled(true);
                }
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Log.e("MARKERCLICEKR", marker.getTitle());
                        showDialogMap(marker.getTitle());
                        return true;
                    }
                });
            }
        });
    }

    private void showDialogMap(String id){
        CurrentEvent.Data event = null;

        for (CurrentEvent.Data data : dataList) {
            if(String.valueOf(data.getId()).equals(id)){
                event = data;
            }
        }

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.event_dialog_map);
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


        joinTeam.setVisibility(View.GONE);

        createTeam.setVisibility(View.GONE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        googleMap.setMyLocationEnabled(true);
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (mLastLocation != null) {
                            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
                            Log.e("LAT AND LONG123 ", String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));
                            mMapView.onResume();
                        }
                    } catch (SecurityException e) {

                    }

                } else {
                    Toast.makeText(this, "Permission denied to get location!",
                            Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    private void getCurrentEvents() {
        NetworkService networkService = new NetworkService();
        networkService.getAPI().getAllEvents().enqueue(new Callback<CurrentEvent>() {
            @Override
            public void onResponse(Call<CurrentEvent> call, Response<CurrentEvent> response) {
//                if(response.body().isSuccsess()){
                dataList.clear();
                dataList.addAll(response.body().getDataList());
                Log.i("DATA", "DATA JERE");


                for (CurrentEvent.Data data : dataList) {
                    LatLng latLng = new LatLng(Double.parseDouble(data.getLat()), Double.parseDouble(data.getLng()));

                    MarkerOptions marker = new MarkerOptions().position(latLng).title(String.valueOf(data.getId()));

                    //TODO CHCECK THIS
//                    marker.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(getResources().getDrawable(R.mipmap.ic_launcher),data.getName(),data.getStart_at())));
                    marker.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView("http://files.softicons.com/download/holidays-icons/halloween-icon-set-by-yootheme/png/512x512/pumpkin.png", data.getName(), data.getStart_at())));


                    googleMap.addMarker(marker);
                }


//                }
            }

            @Override
            public void onFailure(Call<CurrentEvent> call, Throwable t) {
                Log.i("ERROR", "DATA JERE");

            }
        });
    }

    private Bitmap getMarkerBitmapFromView(String image, String outletName, String outletProduct) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.outlet_image);
        TextView outletTitle = (TextView) customMarkerView.findViewById(R.id.outlet_title);
        TextView outletProductCustom = (TextView) customMarkerView.findViewById(R.id.outlet_product);


//        markerImageView.setImageDrawable(drawable1);
        Picasso.with(getApplicationContext()).load(image).into(markerImageView);
        outletTitle.setText(outletName);
        outletProductCustom.setText(outletProduct);

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();

        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            Log.e("LAT AND LONG ", String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));


            getCurrentEvents();

        } else {
//            HomeListFragment.noItemsInList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

//TODO KREIRANJE DIALOGA


//    private void showDialogHomeListFragment(final Outlets clickedOutlet) {
//        if (isLoaded) {
//            final Dialog dialog = new Dialog(ctx);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.home_list_dialog);
//
//            TextView start = (TextView) dialog.findViewById(R.id.start);
//            TextView title = (TextView) dialog.findViewById(R.id.restaurant_title);
//            TextView product = (TextView) dialog.findViewById(R.id.product);
//            TextView text = (TextView) dialog.findViewById(R.id.text);
//            ImageView close = (ImageView) dialog.findViewById(R.id.close);
//            final ImageView restImage = (ImageView) dialog.findViewById(R.id.restaurant_image);
//
//            title.setText(clickedOutlet.getName());
//            product.setText(clickedOutlet.getReward());
////                restImage.setImageDrawable(this.restImage.getDrawable());
//
//            Picasso.with(ctx).load(clickedOutlet.getParseFile("logo").getUrl()).into(restImage);
//            List<String> distanceSplited = Parse(clickedOutlet.getDistanceBetweenUserAndOutlet().toString());
//
////            Log.e("DISTANCE", distanceSplited.get(0));
//
//            float distanceInMetters = Float.parseFloat(distanceSplited.get(0)) * kmToM;
//            Log.e("DISTANCE METTERS", String.valueOf(distanceInMetters));
////            if(distanceInMetters <= outletCloseEnough){
//            if (clickedOutlet.getName().equals("Barnabee's")) {   //TODO THIS IS JUST FOR TESTING CHAT ACTIVITY
//
//                text.setText(R.string.close_enough);
//                start.setText(R.string.start);
//                start.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                            Log.e("outletsID", outletsID.toString());
//                        Intent intent = new Intent(ctx, ChatActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra(QUESTION, clickedOutlet.getOutletID());
//                        intent.putExtra(OUTLET_NAME, clickedOutlet.getName());
//                        intent.putExtra(IMAGE_URL, clickedOutlet.getParseFile("logo").getUrl());
//                        ctx.startActivity(intent);
//
//                        isLoaded = false;
//                        dialog.dismiss();
//
//                    }
//                });
//            } else {
//                text.setText(R.string.to_far);
//                start.setText(R.string.ok);
//                start.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        isLoaded = false;
//                        dialog.dismiss();
//                    }
//                });
//            }
//
//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//
//        }
//    }

