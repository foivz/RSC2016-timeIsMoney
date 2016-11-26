package com.rsc.rschackathon.adapters;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.GetTeamMembersAPI;
import com.squareup.picasso.Picasso;

import android.content.Context;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyDevicesViewHolder> {

    final Context context;

    public TeamListAdapter(Context context) {
        this.context = context;
    }

    public interface Listener {

        Listener EMPTY = new EmptyListener();

        void getDeviceAtPosition(int position);
    }

    private Listener listener = Listener.EMPTY;

    List<GetTeamMembersAPI.Data> teamList = new ArrayList<>();


    @Override
    public TeamListAdapter.MyDevicesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        return new MyDevicesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TeamListAdapter.MyDevicesViewHolder holder, final int position) {
        //Log.i("TAG", teamList.getData().get(position).getName());
        holder.teamMate.setText(teamList.get(position).getName());
        Picasso.with(context)
                .load(teamList.get(position).getAvatar_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.profileIcon);
    }

    @Override
    public int getItemCount() {

        return teamList.size();
    }

    public void setData(List<GetTeamMembersAPI.Data> data) {
        teamList = data;
        //Log.i("TAG", teamList.getData().get(0).getName());
        notifyDataSetChanged();
    }

    public void setListener(final Listener listener) {
        this.listener = listener != null ? listener : Listener.EMPTY;
    }


    public class MyDevicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_mate)
        protected TextView teamMate;

        @BindView(R.id.user_profile_icon)
        protected CircleImageView profileIcon;

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
