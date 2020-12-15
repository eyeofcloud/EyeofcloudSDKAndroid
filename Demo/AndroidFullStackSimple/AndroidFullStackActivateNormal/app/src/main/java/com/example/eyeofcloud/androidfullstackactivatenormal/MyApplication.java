package com.example.eyeofcloud.androidfullstackactivatenormal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.eyeofcloud.ab.android.sdk.EyeofcloudManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyApplication extends Application {

    public static final String PROJECT_ID = "641_4f26904d4443d2d24112fe44784cb74c";
    private EyeofcloudManager eyeofcloudManager;

    public EyeofcloudManager getEyeofcloudManager() {
        return eyeofcloudManager;
    }

    public Map<String,String> getAttributes() {
        Map<String,String> attributes = new HashMap<>();
        attributes.put("gender", "female");
        return attributes;
    }

    @NonNull
    public String getUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", null);
        if (name == null) {
            name = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("userName", name).apply();
        }
        return name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        eyeofcloudManager = EyeofcloudManager.builder(PROJECT_ID).build();
    }
}