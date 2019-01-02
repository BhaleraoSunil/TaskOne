package com.taskone.taskone.webapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(logging);

            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build();
        }
        return retrofit;
    }

}
