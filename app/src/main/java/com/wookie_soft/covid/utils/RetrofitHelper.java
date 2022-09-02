package com.wookie_soft.covid.utils;

import com.wookie_soft.covid.data.model.ApiResponse;
import com.wookie_soft.covid.data.repository.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper instance;
    private static final String baseUrl = "https://api.odcloud.kr/api/";
    private static final String serviceKey = "Pmg4W4DEq17wNe/4EabMJ28ZLWNet3tl3qwsXcm+8f6/MoQOlAenP3cBwR5lAF4z3g/5HnkFXgai/hnzcw5G7Q==";

    public RetrofitService service = new Retrofit.Builder()
            .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService.class);

    public static RetrofitHelper getInstance(){
        if(instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }





}
