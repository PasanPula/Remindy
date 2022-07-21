package com.usj.remindy;

public class MedicineDataModel {
    private int id;
    private String type;
    private String medicine;
    private String dose;
    private String time;

    public MedicineDataModel(int id, String type, String medicine, String dose, String time) {
        this.id = id;
        this.type = type;
        this.medicine = medicine;
        this.dose = dose;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

