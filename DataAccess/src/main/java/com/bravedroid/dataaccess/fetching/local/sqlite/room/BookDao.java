package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllBooks(Book... books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneBook(Book book);

    @Query("DELETE FROM books")
    void deleteAll();
}
