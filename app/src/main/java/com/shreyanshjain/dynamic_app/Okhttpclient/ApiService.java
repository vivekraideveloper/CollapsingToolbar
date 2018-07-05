package com.shreyanshjain.dynamic_app.Okhttpclient;

import com.shreyanshjain.dynamic_app.Models.TestResults;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<TestResults> results(@Url String url);

    @FormUrlEncoded
    @POST
    Call<TestResults> getUser(@Url String url, @FieldMap HashMap<String,String> map);
}
