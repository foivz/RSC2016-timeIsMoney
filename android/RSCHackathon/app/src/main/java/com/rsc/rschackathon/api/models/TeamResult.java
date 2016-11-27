package com.rsc.rschackathon.api.models;

import java.util.List;

public class TeamResult {

    boolean success;

    List<Data> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        String team_id;

        String team_name;

        String score_sum;

        public String getTeam_id() {
            return team_id;
        }

        public void setTeam_id(String team_id) {
            this.team_id = team_id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getScore_sum() {
            return score_sum;
        }

        public void setScore_sum(String score_sum) {
            this.score_sum = score_sum;
        }
    }
}
