package com.wookie_soft.covid.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;


import android.os.Bundle;
import android.util.Log;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.wookie_soft.covid.R;
import com.wookie_soft.covid.data.model.User;
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
    private MyVeiwModel vm;


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


        // ViewModel 불러오기
         vm = new ViewModelProvider(this).get(MyVeiwModel.class);
         vm.getApiDataToNet();


         final Observer<User> userObserver = new Observer<User>() {
             @Override
             public void onChanged(User user) {
                 // 사용자의 위치 정보 변경시, 네이버 지도 좌표 업뎃.

             }
         };

        vm.getData().observe(this,data -> {
            // 여기서 위치정보 가져오기

        });


    }

    @Override // 네이버 맵 객체 획득 후, 이벤트 코드.
    public void onMapReady(@NonNull NaverMap naverMap) {

        naverMap.setNightModeEnabled(true);// 이쁜 야간모드 지원 ㅎㅎ
        // 요기서 마커 불러오고.. 말풍선도 만들기..


    }



}