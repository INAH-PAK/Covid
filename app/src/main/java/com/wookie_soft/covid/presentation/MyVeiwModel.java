package com.wookie_soft.covid.presentation;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

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
    //

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

    //API에서 데이터 받아와서 룸에 저장
    public MutableLiveData<List<Data>> getApiDataToNet() {
        String baseUrl = "https://api.odcloud.kr/api/";

        Retrofit retrofit = RetrofitHelper.getRetrofit(baseUrl);
        RetrofitService service = retrofit.create(RetrofitService.class);
        MutableLiveData<List<Data>> liveData = new MutableLiveData<>();

        Call<ApiResponse> call = service.getApiToData("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("api", response.body().data.get(0).address.toString());
                if(response.isSuccessful()){
                    liveData.postValue(response.body().data); // 룸에 넣었음. 쓰는건 백그라운드 스레드에서 해야함 -> 이때 코루틴, RxJava
                   // new InsertDataThread(response.body().data).start();

                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("api","네트워크 서버 연결 실패 ");
            }
        });
        return liveData;
    }

    // 내가 직접 구현한 스레드 -> Executors 를 사용하면 알아서 스레드만들어서 병렬처리 해줌.
    // 데이터베이스 ㄱㄱ
//
//    class InsertDataThread extends Thread{ // 네크워크 작업하는 스레드
//        List<Data> data;
//        public InsertDataThread(List<Data> data){
//            this.data = data;
//        }
//        @Override
//        public void run() {
//            dao.deleteAll(); // 기존 데이터 모두 삭제
//            dao.insertAll(data.toArray(new Data[data.size()]));
//            Log.i("sss",dao.getAll().toString());
//            List<Data> f = dao.getAll();
//            Log.i("room",f.get(0).address.toString());
//
//        }
//    }


    private void loadData() {

    }

    // 버튼 클릭시 , 사용자의 현재 위치 업뎃
    public void onClickHandler() {
        // 위치 콜백 메소드 다시


    }

    public void getApiDataToString() {
        String baseUrl = "https://api.odcloud.kr/api/";

        Retrofit retrofit = RetrofitHelper.getRetrofit(baseUrl);
        RetrofitService service = retrofit.create(RetrofitService.class);


        Call<String> call = service.getApiToString("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                Log.i("api", response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("api", t.toString());

            }
        });


    }


}
