package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.DatabaseView;

@DatabaseView("SELECT user.uid, user.firstName, user.postCode, user.street" + "FROM User")
public class UserDetail {
    public String uid;
    public String firstName;
    public int postCode;
    public String street;

}

