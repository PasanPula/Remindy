package com.usj.remindy;

import java.util.Date;

public class ExpiryItemModel {
    private int id;
    private String ItemName;
    private String ItemDesc;
    private String Expdate;

    public ExpiryItemModel(int id, String itemName, String itemDesc, String expdate) {
        this.id = id;
        ItemName = itemName;
        ItemDesc = itemDesc;
        Expdate = expdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public void setItemDesc(String itemDesc) {
        ItemDesc = itemDesc;
    }

    public String getExpdate() {
        return Expdate;
    }

    public void setExpdate(String expdate) {
        Expdate = expdate;
    }
}