package com.wookie_soft.covid.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;


import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.wookie_soft.covid.R;
import com.wookie_soft.covid.databinding.ActivityMainBinding;
import com.wookie_soft.covid.data.model.ApiResponse;
import com.wookie_soft.covid.utils.SetViewModelFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    ActivityMainBinding binding;
    ApiResponse apiResponse;
    private MyVeiwModel vm;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private Location mlocation;

    private NaverMap naverMap;

    // RxJava
    // https://kangraemin.github.io/android/2021/10/17/rxjava-retrofit-room/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewModel 불러오기
        vm = new ViewModelProvider(this,new SetViewModelFactory(getApplication()))
                .get(MyVeiwModel.class);

        vm.getApiDataToNet();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.setViewmodel(vm);


        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("gu9jq14ykr"));

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.main_map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.main_map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);





//
//         final Observer<ApiLocation> userObserver = new Observer<ApiLocation>() {
//             @Override
//             public void onChanged(ApiLocation user) {
//                 // 사용자의 위치 정보 변경시, 네이버 지도 좌표 업뎃. -> 반응형 코딩 ㄱㄱ
//
//             }
//         };


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                new AlertDialog.Builder(this)
                        .setTitle("경고")
                        .setMessage("앱을 이용하시려면 위치 정보 사용 권한이 필요합니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                                dialog.dismiss();
                            }
                        }).create().show();
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override // 네이버 맵 객체 획득 후, 이벤트 코드.
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setNightModeEnabled(true);// 이쁜 야간모드 지원 ㅎㅎ

        naverMap.setLocationSource(locationSource);
        // 요기서 마커 불러오고.. 말풍선도 만들기..
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        naverMap.setMapType(NaverMap.MapType.Basic);




    }

    public void getLocationSource(){

        naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
               mlocation.set(location);

                LatLng coord = new LatLng(location);

                LocationOverlay locationOverlay = naverMap.getLocationOverlay();
                locationOverlay.setVisible(true);
                locationOverlay.setPosition(coord);
                locationOverlay.setBearing(location.getBearing());

                naverMap.moveCamera(CameraUpdate.scrollTo(coord));



            }
        });

    }









}