package com.wookie_soft.covid.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wookie_soft.covid.data.model.Data;


@Database(entities = {Data.class}, version = 1)
public abstract class ApiDatabase extends RoomDatabase {
    private static ApiDatabase instance;

    public abstract DataDao getDataDao();

    public static synchronized ApiDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ApiDatabase.class,"api_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }




}
