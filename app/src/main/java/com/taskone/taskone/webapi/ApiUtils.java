package com.taskone.taskone.webapi;



public class ApiUtils {

    private ApiUtils() {}

    private static final String BASE_URL = "https://api.github.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
