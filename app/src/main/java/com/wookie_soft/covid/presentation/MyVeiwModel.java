package com.wookie_soft.covid.presentation;


import android.app.Application;
import android.location.Location;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wookie_soft.covid.data.model.Data;
import com.wookie_soft.covid.data.repository.ApiRepository;

import java.util.List;

public class MyVeiwModel extends AndroidViewModel {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    // AndroidViewModel :
    // https://developer.android.com/reference/androidx/lifecycle/AndroidViewModel?hl=ko

    private ApiRepository repository;
    private Application application;
    List<Data> data;
    public Location l;


    //https://comoi.io/300
    // MutableLiveData , LiveData
    public MutableLiveData<Location> liveLocation; // 사용자의 현재 위치가 계속 업데이트되는 값.
    public MutableLiveData<Location> insLocation; // 지도 이동용 값
    public int i = 0;

    public MyVeiwModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new ApiRepository(application);
        data = repository.getAllData();
    }
    List<Data> getAllData(){
        return data;
    }


    public void locationUpdate(Location location) {
        if(liveLocation == null) {
            l.setLatitude(37.5666805);
            l.setLongitude(126.9784147);
            liveLocation.postValue(l);
        }
            liveLocation.setValue(location);
        }

    //insert
    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public void getApiDataToNet(){ //  레포지토리에서 레트로핏작업 해달라고 요청
         repository.getApiDataToNet();
    }



}
