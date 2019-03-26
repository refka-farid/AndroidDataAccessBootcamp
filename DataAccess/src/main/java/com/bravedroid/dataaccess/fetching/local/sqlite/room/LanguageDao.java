package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLanguages(Language... languages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneLanguage(Language language);

    @Query("DELETE FROM language")
    void deleteAll();

}
