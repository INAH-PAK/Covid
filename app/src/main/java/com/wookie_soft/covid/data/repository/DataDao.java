package com.wookie_soft.covid.data.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wookie_soft.covid.data.model.Data;

import java.util.List;



@Dao
public interface DataDao {

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

}
