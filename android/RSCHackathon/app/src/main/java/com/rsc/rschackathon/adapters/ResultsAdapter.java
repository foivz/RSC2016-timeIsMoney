package com.rsc.rschackathon.adapters;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.TeamResult;

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
import de.hdodenhof.circleimageview.CircleImageView;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    List<TeamResult.Data> teamList = new ArrayList<>();
    Context context;

    @Override
    public ResultsAdapter.ResultsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_item, parent, false);
        return new ResultsAdapter.ResultsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ResultsAdapter.ResultsViewHolder holder, final int position) {
        holder.teamName.setText(teamList.get(position).getTeam_name());
        holder.teamScore.setText(String.valueOf(teamList.get(position).getScore_sum()));
        if(position == 0){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.gold));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }else if(position == 1){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.silver));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }else if(position == 2){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.bronze));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.line.setVisibility(View.VISIBLE);
        }else{
            holder.teamPosition.setVisibility(View.INVISIBLE);
            holder.teamPositionText.setVisibility(View.VISIBLE);
            holder.teamPositionText.setText(String.valueOf(position + 1));
            holder.line.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setData(List<TeamResult.Data> data, Context context) {
        teamList.clear();
        this.context = context;
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

        @BindView(R.id.text_position)
        TextView teamPositionText;

        @BindView(R.id.line)
        View line;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}