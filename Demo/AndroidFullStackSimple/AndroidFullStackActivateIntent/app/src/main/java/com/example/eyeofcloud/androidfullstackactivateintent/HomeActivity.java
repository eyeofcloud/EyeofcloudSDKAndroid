package com.example.eyeofcloud.androidfullstackactivateintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient;
import com.eyeofcloud.ab.config.Variation;

import java.util.Map;

public class HomeActivity extends Activity {
    private MyApplication application;
    private String userName;
    private Map<String, String> attributes;
    private EyeofcloudClient eyeofcloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        application = (MyApplication) getApplication();
        userName = application.getUserName();
        attributes = application.getAttributes();
        eyeofcloud = application.getEyeofcloudManager().getEyeofcloud();

        Button openButton = (Button)findViewById(R.id.button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(application.getBaseContext(), DefaultActivity.class);
                Variation variation = eyeofcloud.activate("android-fullstack-activate-intent", userName, attributes);
                if (variation != null) {
                    if (variation.getKey().equals("优化版本A")) {
                        intent = new Intent(application.getBaseContext(), VariationAActivity.class);
                    } else if (variation.getKey().equals("优化版本B")) {
                        intent = new Intent(application.getBaseContext(), VariationBActivity.class);
                    }
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
