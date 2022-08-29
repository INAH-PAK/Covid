package com.wookie_soft.covid.data.repository;

import android.app.Application;

import com.wookie_soft.covid.data.model.Data;

import java.util.List;

public class ApiRepository {

    // Repository의 역할 : 데이터 소스의 위치(서버, Local DB)를 몰라도, 일관성 있게 원하는 데이터를 취할 수 있도록 돕는 것
    // viewModel
    // 그렇다면, 현재 , DAO -> 모델의 실질적인 데이터

    // 나중에 참고 -> 다중 스레드로 네트워크 서버에서 데이터 가져오기ㅏ
    // https://developer.android.com/guide/background/threading

    private  DataDao dao;
    private List<Data> data;

    public ApiRepository(Application application){
        ApiDatabase database = ApiDatabase.getInstance(application);
        DataDao dao = database.getDataDao();
        data = dao.getAll();
    }

    // TODO 레포지토리 자료 인서트 딜리트 부분 정리하기
    // : https://frtt0608.tistory.com/104
    public void insert(Data data){
    //    new Insert
    }





}
