package com.rsc.rschackathon.api.models;

import java.util.ArrayList;
import java.util.List;

public class CurrentEvent {
    boolean succsess;
    List<Data> data;

    public boolean isSuccsess() {
        return succsess;
    }

    public void setSuccsess(boolean succsess) {
        this.succsess = succsess;
    }

    public List<Data> getDataList() {
        return data;
    }

    public void setDataList(List<Data> dataList) {
        this.data = dataList;
    }

    public class Data{
        int id;
        String status;
        String name;
        String description;
        String location;
        String lat;
        String lng;
        String start_at;
        String team_members;


        public Data(int id, String status, String name, String description, String location, String lat, String lng, String start_at, String team_members) {
            this.id = id;
            this.status = status;
            this.name = name;
            this.description = description;
            this.location = location;
            this.lat = lat;
            this.lng = lng;
            this.start_at = start_at;
            this.team_members = team_members;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getStart_at() {
            return start_at;
        }

        public void setStart_at(String start_at) {
            this.start_at = start_at;
        }

        public String getTeam_members() {
            return team_members;
        }

        public void setTeam_members(String team_members) {
            this.team_members = team_members;
        }
    }
}
