package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient;


public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://bydegreestest.agnitioworld.com/test/";

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
