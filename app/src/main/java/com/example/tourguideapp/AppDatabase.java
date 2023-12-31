package com.example.tourguideapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataEntity.class, FavouritesEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

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
    public abstract FavouritesDao favouritesDao();
}

