package com.vijayjaidewan01vivekrai.dynamic_app.Okhttpclient;


public class ApiUtils {

    private ApiUtils() {}

    public static ApiService getAPIService() {
        return RetrofitClient.getClient().create(ApiService.class);
    }


}
