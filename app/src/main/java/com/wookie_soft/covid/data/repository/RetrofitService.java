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

}
