package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public final static class Accessor {
        private static AppDatabase database;

        private Accessor() {
        }

        public synchronized static AppDatabase getInstance(Context context) {
            if (database == null) {
                database = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "ADAB_Room.db").build();
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
