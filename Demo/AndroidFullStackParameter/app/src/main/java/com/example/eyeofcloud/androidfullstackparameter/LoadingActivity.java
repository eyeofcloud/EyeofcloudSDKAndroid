package com.example.eyeofcloud.androidfullstackparameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient;
import com.eyeofcloud.ab.android.sdk.EyeofcloudStartListener;

public class LoadingActivity extends Activity {

    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        application = (MyApplication) getApplication();
    }

    @Override
    protected void onStart() {
        super.onStart();

        application.getEyeofcloudManager().initialize(this, new EyeofcloudStartListener() {
            @Override
            public void onStart(EyeofcloudClient eyeofcloud) {
                //sleep for showing loading UI
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(application.getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
