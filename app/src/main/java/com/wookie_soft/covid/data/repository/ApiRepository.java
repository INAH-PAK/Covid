package com.wookie_soft.covid.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wookie_soft.covid.data.model.ApiResponse;
import com.wookie_soft.covid.data.model.Data;
import com.wookie_soft.covid.utils.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepository {

    // Repository의 역할 : 데이터 소스의 위치(서버, Local DB)를 몰라도, 일관성 있게 원하는 데이터를 취할 수 있도록 돕는 것
    // viewModel이 얘한테 접근해서 데이터 받아가야 함.
    // 정리하면, DAO에게 정보 받아오ㅗ고, 네트워크에서도 데이터 받아오니, viewmodel은 데이터 작용 신경안쓰고 ㅣLivedata관리가능

    // 나중에 참고 -> 다중 스레드로 네트워크 서버에서 데이터 가져오기ㅏ
    // https://developer.android.com/guide/background/threading


    private DataDao dao;
    private List<Data> data;
    public  RetrofitHelper retrofit = RetrofitHelper.getInstance();

    public ApiRepository(Application application){
        ApiDatabase database = ApiDatabase.getInstance(application);
        DataDao dao = database.getDataDao();
        data = dao.getAll();
    }
    public List<Data> getAllData(){ return data;}

    // CompositeDisposable : RxJava로 데이터 받을때 구독하는데, 이 구독을 한번에 해지하는 인스턴스? 대충 이럼.,
    // => 메모리 누수 방지 !
//    CompositeDisposable compositeDisposable = new CompositeDisposable();


//    public void insertByExccutor(List<Data> data){
//        ApiDatabase.databaseWriteExcutor.execute(()->{
//            dao.deleteAll(); // 기존 데이터 모두 삭제
//            dao.insertAll(data.toArray(new Data[data.size()])); // 룸에 데이터 넣으ㅁ
//            Log.i("sss",dao.getAll().toString());
//        });
//    }




    // 테스트 용으로 그냥 찍어보기 (레트로핏 )
//    public void getApiDataToString() {
//        String baseUrl = "https://api.odcloud.kr/api/";
//
//        Retrofit retrofit = RetrofitHelper.getRetrofit(baseUrl);
//        RetrofitService service = retrofit.create(RetrofitService.class);
//
//
//        Call<String> call = service.getApiToString("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
//
//                Log.i("api", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i("api", t.toString());
//
//            }
//        });
//
//    }

    //API에서 데이터 받아와서 룸에 저장
    public List<Data> getApiDataToNet() {

        RetrofitService service = RetrofitHelper.getInstance().service;

        Call<ApiResponse> call = service.getApiToData("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("api", response.body().data.get(0).address.toString());
                if(response.isSuccessful()){

                     new InsertDataThread(response.body().data); // 이걸 RxJava로 !!!
                    data = response.body().data;

                    Log.i("fff",data.get(1).address.toString());

                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("api","네트워크 서버 연결 실패 ");
            }
        });
        return data;
    }



    class InsertDataThread extends Thread{ // 네크워크 작업하는 스레드
        List<Data> data;
        public InsertDataThread(List<Data> data){
            this.data = data;
        }
        @Override
        public void run() {
            dao.deleteAll(); // 기존 데이터 모두 삭제
            dao.insertAll(data.toArray(new Data[data.size()]));
            Log.i("sss",dao.getAll().toString());
            List<Data> f = dao.getAll();
            Log.i("room",f.get(0).address.toString());
        }
    }





}
