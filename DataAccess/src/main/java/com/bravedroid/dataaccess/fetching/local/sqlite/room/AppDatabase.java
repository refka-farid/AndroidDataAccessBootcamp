package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Book.class, Language.class}, version = 1, views = {UserBookDetail.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract BookDao bookDao();

    public abstract LanguageDao LanguageDao();


    public final static class Accessor {
        private static AppDatabase database;

        private Accessor() {
        }

        public synchronized static AppDatabase getInstance(Context context) {
            if (database == null) {
                database = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "ADAB_Room.db")
                        // .fallbackToDestructiveMigration()
                        .fallbackToDestructiveMigrationFrom()
                        //    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                        .build();
            }
            return database;
        }

        @VisibleForTesting
        public synchronized static AppDatabase getInstanceForTest(Context context) {
            if (database == null) {
                database = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                        AppDatabase.class)
                        //.allowMainThreadQueries()
                        .build();
            }
            return database;
        }
    }
}
