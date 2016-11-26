package com.rsc.rschackathon.adapters;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.CurrentEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyDevicesViewHolder> {

    private static final float kmToM = 1000F;

    public interface Listener {

        Listener EMPTY = new EmptyListener();

        void getDeviceAtPosition(int position);
    }

    private Listener listener = Listener.EMPTY;

    List<CurrentEvent.Data> currentEventList = new ArrayList<>();
    private double longitude;
    private double latitude;

    @Override
    public RecyclerViewAdapter.MyDevicesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyDevicesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.MyDevicesViewHolder holder, final int position) {
        holder.number.setText(String.valueOf(position + 1));
        holder.name.setText(currentEventList.get(position).getName());
        holder.location.setText(currentEventList.get(position).getLocation());

        String [] time = currentEventList.get(position).getStart_at().split(" ");

        holder.startTime.setText("Starts at " + time[1]);
        holder.teamMembers.setText(currentEventList.get(position).getTeam_members());
        float[] results = new float[1];

        Location.distanceBetween(latitude, longitude, Double.parseDouble(currentEventList.get(position).getLat()), Double.parseDouble(currentEventList.get(position).getLng()), results);

        BigDecimal bd = new BigDecimal(results[0]).setScale(1, RoundingMode.HALF_EVEN);
        results[0] = bd.floatValue();
        if(results[0] < 1000) {
            holder.distance.setText(results[0] + " m");
        }
        else{
            results[0] = results[0] / kmToM;
            holder.distance.setText(results[0] + " km");

        }


    }

    @Override
    public int getItemCount() {
        return currentEventList.size();
    }

    public void setData(List<CurrentEvent.Data> data, double latitude, double longitude) {
        currentEventList.clear();
        currentEventList.addAll(data);
        notifyDataSetChanged();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setListener(final Listener listener) {
        this.listener = listener != null ? listener : Listener.EMPTY;
    }


    public class MyDevicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        protected TextView number;

        @BindView(R.id.name)
        protected TextView name;

        @BindView(R.id.distance)
        protected TextView distance;

        @BindView(R.id.location)
        protected TextView location;

        @BindView(R.id.team_members)
        protected TextView teamMembers;

        @BindView(R.id.start_time)
        protected TextView startTime;

        @OnClick(R.id.device_item_layout)
        public void onItemClicked(View view) {
            listener.getDeviceAtPosition(getAdapterPosition());
        }

        public MyDevicesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final class EmptyListener implements Listener {

        @Override
        public void getDeviceAtPosition(int position) {
            //NO OP
        }
    }

}