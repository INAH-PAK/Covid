package com.wookie_soft.covid.presentation;


import android.app.Application;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.naver.maps.map.LocationSource;
import com.naver.maps.map.util.FusedLocationSource;
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
    private MutableLiveData<LocationSource> currentLocation;

    public MyVeiwModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new ApiRepository(application);
        data = repository.getAllData();


    }
    List<Data> getAllData(){
        return data;
    }

    public MutableLiveData<String> getCurrentName() {
        if (currentLocation == null) {
            currentLocation = new MutableLiveData<LocationSource>();
        }
        return currentLocation;
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

    // 위치 값





    // 버튼 클릭시 , 사용자의 현재 위치 업뎃
    public void onClickHandler(View view) {
        Log.i("Ddd", "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        // 사용자의 현재 위치 값으로 지도 이동



        Log.i("Ddd", location.getLastLocation().toString());
        Log.i("Ddd", location.getLastLocation().getLatitude()+"");

    }





}
