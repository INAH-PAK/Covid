package com.wookie_soft.covid.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wookie_soft.covid.data.model.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Data.class}, version = 1, exportSchema = true)
public abstract class ApiDatabase extends RoomDatabase {
    // DataBase : 데이터 베이스 이용을 위한 DAO 객체 획득 함수를 제공하는 클래스.
    // 디비를 사용하기 위해 가장 먼저 호출 !!!

    //https://roomedia.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-RoomDatabase-in-Java#toc-RoomDatabase%20Singleton

    // volatile :  캐시가 아닌 메인 메모리에서 읽고 쓰겠다! -> 여러 스레드에서 사용할 수 도 있으니까 , , ,
    private static volatile ApiDatabase instance;

    public abstract DataDao getDataDao();
    public static final int NUMBER_OF_THREADS = 3; // 최대 스레드 수

    // ExecutorService : java의 스레드 최상위 api
    public static final ExecutorService databaseWriteExcutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized ApiDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ApiDatabase.class,"api_database")
                    .fallbackToDestructiveMigration() // Migration : 이주, 이동 -> 이거 룸 충돌?나면 기존꺼 다 지워버리니까 앱 출시했으면 절대 안도opppㅐㅐㅔㅔ
                    .build();
        }
        return instance;
    }

}
