package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "language",
       indices = {@Index(value = {"name"})}
)
public class Language {
    @NonNull
    @PrimaryKey
    public String name;
    public int popularity;
}
