package com.rsc.rschackathon.adapters;

import com.rsc.rschackathon.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyDevicesViewHolder> {

    public interface Listener {

        Listener EMPTY = new EmptyListener();

        void getDeviceAtPosition(int position);
    }

    private Listener listener = Listener.EMPTY;

    List<String> teamList = new ArrayList<>();


    @Override
    public TeamListAdapter.MyDevicesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        return new MyDevicesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TeamListAdapter.MyDevicesViewHolder holder, final int position) {
        holder.teamMate.setText(teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setData(List<String> data) {
        teamList.clear();
        teamList.addAll(data);
        notifyDataSetChanged();
    }

    public void setListener(final Listener listener) {
        this.listener = listener != null ? listener : Listener.EMPTY;
    }


    public class MyDevicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_mate)
        protected TextView teamMate;

        @OnClick(R.id.team_list_layout)
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
