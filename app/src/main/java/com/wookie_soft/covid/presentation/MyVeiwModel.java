package com.wookie_soft.covid.presentation;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.webkit.PermissionRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.naver.maps.map.util.FusedLocationSource;
import com.wookie_soft.covid.data.model.ApiResponse;
import com.wookie_soft.covid.data.model.Data;
import com.wookie_soft.covid.data.model.User;
import com.wookie_soft.covid.data.repository.ApiDatabase;
import com.wookie_soft.covid.data.repository.ApiRepository;
import com.wookie_soft.covid.data.repository.DataDao;
import com.wookie_soft.covid.data.repository.RetrofitService;
import com.wookie_soft.covid.utils.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
