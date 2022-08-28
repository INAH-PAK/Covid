package com.wookie_soft.covid.data.repository;

import android.app.Application;

import com.wookie_soft.covid.data.model.Data;

import java.util.List;

public class ApiRepository {
    private  DataDao dao;
    private List<Data> data;

    public ApiRepository(Application application){
        ApiDatabase database = ApiDatabase.getInstance(application);
        DataDao dao = database.getDataDao();
        data = dao.getAll();
    }

    // TODO 레포지토리 자료 인서트 딜리트 부분 정리하기
    // : https://frtt0608.tistory.com/104
//    public void insert(Data data){
//        new Insert
//    }





}
