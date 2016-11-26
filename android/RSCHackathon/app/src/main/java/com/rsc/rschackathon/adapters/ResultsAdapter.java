package com.rsc.rschackathon.adapters;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.TeamResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    List<TeamResult> teamList = new ArrayList<>();

    @Override
    public ResultsAdapter.ResultsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        return new ResultsAdapter.ResultsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ResultsAdapter.ResultsViewHolder holder, final int position) {
            //TODO PUT RESULTS HERE
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setData(List<TeamResult> data) {
        teamList.clear();
        teamList.addAll(data);
        notifyDataSetChanged();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.position)
        CircleImageView teamPosition;

        @BindView(R.id.team_name)
        TextView teamName;

        @BindView(R.id.score)
        TextView teamScore;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}