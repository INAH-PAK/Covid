package com.wookie_soft.covid.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.wookie_soft.covid.data.model.Data;
import com.wookie_soft.covid.data.repository.ApiRepository;

import java.util.List;

public class MyVeiwModel extends AndroidViewModel {
    // AndroidViewModel :
    // https://developer.android.com/reference/androidx/lifecycle/AndroidViewModel?hl=ko

    private ApiRepository repository;
    List<Data> data;

    public MyVeiwModel(@NonNull Application application) {
        super(application);
        repository = new ApiRepository(application);
        data = repository.getAllData();
    }
    List<Data> getAllData(){
        return data;
    }

   // List<Marker> getAllLocation(){ return }

    //insert
    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public void getApiDataToNet(){ //  레포지토리에서 레트로핏작업 해달라고 요청
         repository.getApiDataToNet();
    }

    // 위치 퍼미션


    // 버튼 클릭시 , 사용자의 현재 위치 업뎃
    public void onClickHandler() {
        // 위치 콜백 메소드 다시


    }




}
