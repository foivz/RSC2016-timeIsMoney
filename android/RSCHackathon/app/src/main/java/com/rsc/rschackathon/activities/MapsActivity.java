package com.rsc.rschackathon.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rsc.rschackathon.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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



//TODO CREATION OF CUSTOM MARKER
//TODO AND CALLING

//marker.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(rewardsViewHolder.restImage.getDrawable(),outletsList.get(i).getName(),outletsList.get(i).getReward())));


//    private Bitmap getMarkerBitmapFromView(Drawable drawable1, String outletName, String outletProduct) {
//
//        View customMarkerView = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
//        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.outlet_image);
//        TextView outletTitle = (TextView) customMarkerView.findViewById(R.id.outlet_title);
//        TextView outletProductCustom = (TextView) customMarkerView.findViewById(R.id.outlet_product);
//
////        markerImageView.setImageDrawable(drawable1);
//
//        markerImageView.setImageDrawable(drawable1);
//        outletTitle.setText(outletName);
//        outletProductCustom.setText(outletProduct);
//
//        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
//        customMarkerView.buildDrawingCache();
//
//        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
//                Bitmap.Config.ARGB_8888);
//
//        Canvas canvas = new Canvas(returnedBitmap);
//        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        Drawable drawable = customMarkerView.getBackground();
//        if (drawable != null)
//            drawable.draw(canvas);
//        customMarkerView.draw(canvas);
//        return returnedBitmap;
//    }