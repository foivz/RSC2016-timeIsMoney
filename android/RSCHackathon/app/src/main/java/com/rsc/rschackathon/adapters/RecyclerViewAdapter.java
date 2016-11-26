package com.rsc.rschackathon.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsc.rschackathon.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyDevicesViewHolder> {

    public interface Listener {

        Listener EMPTY = new EmptyListener();

        void getDeviceAtPosition(int position);
    }

    private Listener listener = Listener.EMPTY;

    List<String> deviceList = new ArrayList<>();


    @Override
    public RecyclerViewAdapter.MyDevicesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyDevicesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.MyDevicesViewHolder holder, final int position) {
        holder.number.setText(String.valueOf(position + 1));
        holder.name.setText(deviceList.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void setData(List<String> data) {
        deviceList.clear();
        deviceList.addAll(data);
        notifyDataSetChanged();
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