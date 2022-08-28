package com.wookie_soft.covid.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.util.Log;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.wookie_soft.covid.R;
import com.wookie_soft.covid.databinding.ActivityMainBinding;
import com.wookie_soft.covid.data.model.ApiResponse;
import com.wookie_soft.covid.utils.RetrofitHelper;
import com.wookie_soft.covid.data.repository.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    ActivityMainBinding binding;
    ApiResponse apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("gu9jq14ykr"));

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.main_map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.main_map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

       // getApiDataToString();
        getApiDataToData();



    }

    @Override // 네이버 맵 객체 획득 후, 이벤트 코드.
    public void onMapReady(@NonNull NaverMap naverMap) {


        naverMap.setNightModeEnabled(true);// 이쁜 야간모드 지원 ㅎㅎ
        // 요기서 마커 불러오고.. 말풍선도 만들기..


    }

    public void getApiDataToString(){
        String baseUrl = "https://api.odcloud.kr/api/";

        Retrofit retrofit = RetrofitHelper.getRetrofit(baseUrl);
        RetrofitService service = retrofit.create(RetrofitService.class);


        Call<String> call = service.getApiToString("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                Log.i("api",response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("api",t.toString());

            }
        });


    }
    public void getApiDataToData(){
        String baseUrl = "https://api.odcloud.kr/api/";

        Retrofit retrofit = RetrofitHelper.getRetrofit(baseUrl);
        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<ApiResponse> call = service.getApiToData("Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                        Log.i("api",response.body().data.get(0).address.toString());
                        apiResponse = response.body();
                Log.i("api",apiResponse.data.get(0).centerName.toString());



            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });


    }


}