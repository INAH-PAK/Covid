package com.wookie_soft.covid.data.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wookie_soft.covid.data.model.Data;

import java.util.List;


@Dao
public interface DataDao {

    // Not RxJava
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Query("SELECT * FROM data WHERE uid IN (:userIds)")
    List<Data> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Data... data);

    @Delete
    void delete(Data data);

    @Query("DELETE FROM data")
    void deleteAll();

    // RxJava
    // Single : 구독하는 곳에서 통신 결과를 '수신'만 가능
    // Completable : 통신의 성공 여부만 알 수 있음.
//    @Query("SELECT * FROM data")
//    Single<List<Data>> getAllByRx();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Completable insertAllByRx(Data... data);
//
//    @Query("DELETE FROM data")
//    Completable deleteAllRx();






}
