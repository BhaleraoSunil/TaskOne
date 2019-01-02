
package com.taskone.taskone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GitHubUser extends RealmObject {

    @SerializedName("login")
    @Expose
    private String login;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }
}
