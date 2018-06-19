package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient;


import java.net.URL;

public class ApiUtils {

    private ApiUtils() {}

    public static ApiService getAPIService() {
        return RetrofitClient.getClient().create(ApiService.class);
    }


}
