package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.room.*;

@Fts4(languageId = "lid")
@Entity(tableName = "books",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "uid",
                childColumns = "user_id"))
public class Book {
    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;

    @ColumnInfo(name = "book_title")
    public String title;

    @Ignore
    Bitmap picture;

    @ColumnInfo(name = "lid")
    int languageId;

    @ColumnInfo(name = "user_id")
    @NonNull
    public String userId;
}
