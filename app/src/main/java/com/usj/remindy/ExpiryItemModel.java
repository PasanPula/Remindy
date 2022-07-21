package com.usj.remindy;

import java.util.Date;

public class ExpiryItemModel {
    private String ItemName;
    private String ItemDesc;
    private String Expdate;

    ExpiryItemModel(String item, String desc,String date)
    {
        this.ItemName=item;
        this.ItemDesc=desc;
        this.Expdate=date;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public String getExpdate() {
        return Expdate;
    }


}
