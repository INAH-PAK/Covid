package com.wookie_soft.covid.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.wookie_soft.covid.data.model.Data;

import java.util.List;

public class ApiRepository {

    // Repository의 역할 : 데이터 소스의 위치(서버, Local DB)를 몰라도, 일관성 있게 원하는 데이터를 취할 수 있도록 돕는 것
    // viewModel이 얘한테 접근해서 데이터 받아가야 함.
    // 정리하면, DAO에게 정보 받아오ㅗ고, 네트워크에서도 데이터 받아오니, viewmodel은 데이터 작용 신경안쓰고 ㅣLivedata관리가능

    // 나중에 참고 -> 다중 스레드로 네트워크 서버에서 데이터 가져오기ㅏ
    // https://developer.android.com/guide/background/threading

    private  DataDao dao;
    private List<Data> data;

    public ApiRepository(Application application){
        ApiDatabase database = ApiDatabase.getInstance(application);
        DataDao dao = database.getDataDao();
        data = dao.getAll();
    }
    public List<Data> getAllData(){ return data;}

    public void insert(List<Data> data){
        ApiDatabase.databaseWriteExcutor.execute(()->{
          //
        });
    }







}
