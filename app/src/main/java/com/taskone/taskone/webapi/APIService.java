package com.taskone.taskone.webapi;

import com.taskone.taskone.model.GitHubUser;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("users")
    Call<RealmList<GitHubUser>> getUsers();

}
