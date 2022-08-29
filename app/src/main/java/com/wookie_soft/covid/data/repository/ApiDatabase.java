package com.wookie_soft.covid.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wookie_soft.covid.data.model.Data;


@Database(entities = {Data.class}, version = 1, exportSchema = true)
public abstract class ApiDatabase extends RoomDatabase {
    // DataBase : 데이터 베이스 이용을 위한 DAO 객체 획득 함수를 제공하는 클래스.
    // 디비를 사용하기 위해 가장 먼저 호출 !!!

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
