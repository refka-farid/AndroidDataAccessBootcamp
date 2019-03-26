package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.Date;


@Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true),
        @Index(value = {"language_id"})},

        foreignKeys = @ForeignKey(entity = Language.class,
                parentColumns = "name",
                childColumns = "language_id"
        ))
public class User {
    @NonNull
    @PrimaryKey
    public String uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Embedded
    public Address address;

    public String region;
    @ColumnInfo(name = "language_id")
    public String languageId;

    public Date birthday;
}
