package com.taskone.taskone;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskOneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("taskonerealm.realm").build();
        Realm.setDefaultConfiguration(config);

    }
}