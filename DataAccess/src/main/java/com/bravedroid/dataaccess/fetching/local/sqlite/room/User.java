package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.annotation.NonNull;
import androidx.room.*;


@Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
public class User {
    @PrimaryKey
    @NonNull
    public String uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Embedded
    public Address address;
}
