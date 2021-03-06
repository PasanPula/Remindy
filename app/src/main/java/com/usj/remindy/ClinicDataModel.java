package com.usj.remindy;

public class ClinicDataModel {
    private int id;
    private String detail;
    private String hospital;
    private String doctor;
    private String date;
    private String time;

    public ClinicDataModel(int id, String detail, String hospital, String doctor, String date, String time) {
        this.id = id;
        this.detail = detail;
        this.hospital = hospital;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
