package com.rsc.rschackathon.adapters;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.api.models.HighScore;

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

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder> {

    List<HighScore> teamList = new ArrayList<>();
    private Context context;

    @Override
    public HighScoreAdapter.HighScoreViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_item, parent, false);
        return new HighScoreAdapter.HighScoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HighScoreAdapter.HighScoreViewHolder holder, final int position) {
            if(position == 0){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.gold));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.teamName.setText(teamList.get(position).getName());
            holder.teamScore.setText(String.valueOf(teamList.get(position).getScore()));
            holder.line.setVisibility(View.GONE);
        }else if(position == 1){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.silver));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.teamName.setText(teamList.get(position).getName());
            holder.teamScore.setText(String.valueOf(teamList.get(position).getScore()));
            holder.line.setVisibility(View.GONE);
        }else if(position == 2){
            holder.teamPosition.setBackground(context.getResources().getDrawable(R.drawable.bronze));
            holder.teamPositionText.setVisibility(View.GONE);
            holder.teamName.setText(teamList.get(position).getName());
            holder.teamScore.setText(String.valueOf(teamList.get(position).getScore()));
            holder.line.setVisibility(View.VISIBLE);
        }else{
            holder.teamPosition.setVisibility(View.INVISIBLE);
            holder.teamPositionText.setVisibility(View.VISIBLE);
            holder.teamPositionText.setText(String.valueOf(position + 1));
            holder.teamName.setText(teamList.get(position).getName());
            holder.teamScore.setText(String.valueOf(teamList.get(position).getScore()));
            holder.line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setData(List<HighScore> data, Context context) {
        this.context = context;
        teamList.clear();
        teamList.addAll(data);
        notifyDataSetChanged();
    }

    public class HighScoreViewHolder extends RecyclerView.ViewHolder {

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

        public HighScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
