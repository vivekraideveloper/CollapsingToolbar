package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("mobile_app.php")
    Call<TestResults> results();

}
