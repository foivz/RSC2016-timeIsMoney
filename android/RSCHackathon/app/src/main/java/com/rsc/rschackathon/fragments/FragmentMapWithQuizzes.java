package com.rsc.rschackathon.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rsc.rschackathon.R;

import butterknife.ButterKnife;

public class FragmentMapWithQuizzes extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String ARG_EXAMPLE = "this_is_a_constant";
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 10;
    private String podatak;

    MapView mMapView;
    public static GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;

    Location mLastLocation;


    public static FragmentMapWithQuizzes newInstance(String example_argument) {
        FragmentMapWithQuizzes fragmentMapWithQuizzes = new FragmentMapWithQuizzes();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        fragmentMapWithQuizzes.setArguments(args);
        return fragmentMapWithQuizzes;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_view_pager_two_layout, container, false);
        podatak = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with", podatak);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.gmap);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                } else {
                    googleMap.setMyLocationEnabled(true);
                    //googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
                }

//                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                    @Override
//                    public boolean onMarkerClick(Marker marker) {
//                        Log.e("MARKERCLICEKR", marker.getTitle());
//                        showDialogMap(marker.getTitle());
//                        return true;
//                    }
//                });
            }


        });
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatLng latLng = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
//            latitude = mLastLocation.getLatitude();
//            longitude = mLastLocation.getLongitude();
//            SharedPreferencesHolder.setSavedLatitude(String.valueOf(mLastLocation.getLatitude()));
//            SharedPreferencesHolder.setSavedLongitude(String.valueOf(mLastLocation.getLongitude()));
            Log.e("LAT AND LONG ",String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));
//            listener.onLocationLoaded();
//            HomeListFragment.noItemsInList.setVisibility(View.INVISIBLE);
//            MarkerOptions marker = new MarkerOptions().position(latLng).title("1");
//
//            marker.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(getResources().getDrawable(R.mipmap.ic_launcher),"KVIZ 1","POVIJEST")));

            MarkerOptions marker = new MarkerOptions().position(latLng).title("1");

            marker.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(getResources().getDrawable(R.mipmap.ic_launcher),"KVIZ 1","POVIJEST")));
            googleMap.addMarker(marker);

        }
        else {
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        googleMap.setMyLocationEnabled(true);
                        //googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (mLastLocation != null) {
                            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
//                            latitude = mLastLocation.getLatitude();
//                            longitude = mLastLocation.getLongitude();
//                            SharedPreferencesHolder.setSavedLatitude(String.valueOf(mLastLocation.getLatitude()));
//                            SharedPreferencesHolder.setSavedLongitude(String.valueOf(mLastLocation.getLongitude()));
                            Log.e("LAT AND LONG123 ", String.valueOf(mLastLocation.getLatitude()) + "     " + String.valueOf(mLastLocation.getLongitude()));

                            mMapView.onResume();
                        }
                    } catch (SecurityException e) {

                    }

                } else {
                    Toast.makeText(getActivity(), "Permission denied to get location!",
                            Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

        private Bitmap getMarkerBitmapFromView(Drawable drawable1, String outletName, String outletProduct) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.outlet_image);
        TextView outletTitle = (TextView) customMarkerView.findViewById(R.id.outlet_title);
        TextView outletProductCustom = (TextView) customMarkerView.findViewById(R.id.outlet_product);

        markerImageView.setImageDrawable(drawable1);
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