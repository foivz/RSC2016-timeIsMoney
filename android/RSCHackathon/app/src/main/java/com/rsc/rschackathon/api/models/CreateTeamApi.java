package com.rsc.rschackathon.api.models;

import java.util.List;

public class CreateTeamApi {

    boolean success;

    Data data;

    List<String> messages;

    List<String> errorInfo;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<String> errorInfo) {
        this.errorInfo = errorInfo;
    }

    public class Data {

        String event_id;

        String team_name;

        int id;

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}
