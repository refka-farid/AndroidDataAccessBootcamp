package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.ColumnInfo;

public class Address {
    @ColumnInfo(name= "street_name")
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
