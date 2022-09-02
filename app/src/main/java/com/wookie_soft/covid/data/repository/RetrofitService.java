package com.wookie_soft.covid.data.repository;

import com.wookie_soft.covid.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("15077586/v1/centers")
    Call<String> getApiToString( @Query("serviceKey") String serviceKey);

    @GET("15077586/v1/centers")
    Call<ApiResponse> getApiToData(@Query("serviceKey") String serviceKey);

//    // 기존의 Call객체가 아닌, Single 형태의 결과 저장
//    @GET("15077586/v1/centers")
//    Single<ApiResponse> getApiDataByRxJava(@Query("serviceKey") String serviceKey);


}
