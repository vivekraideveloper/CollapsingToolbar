package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<TestResults> results(@Url String url);

    @POST
    Call<TestResults> getUser(@Url String url,@Body User user);
}
