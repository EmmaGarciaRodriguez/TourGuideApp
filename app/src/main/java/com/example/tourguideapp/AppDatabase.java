package com.example.tourguideapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    /*public abstract DataDao dataDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .build();
        }
        return instance;
    }
}*/


    private static final String dbName = "db";
    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getAppDatabase(Context context) {

        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
    public abstract DataDao dataDao();
}

