package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient;


import java.net.URL;

public class ApiUtils {

    private ApiUtils() {}

    public static ApiService getAPIService(String url) {
        return RetrofitClient.getClient(url).create(ApiService.class);
    }


}
