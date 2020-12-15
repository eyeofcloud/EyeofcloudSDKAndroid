package com.example.eyeofcloud.androidfullstackactivateintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient;

import java.util.Map;

public class VariationAActivity extends Activity {

    private MyApplication application;
    private String userName;
    private Map<String, String> attributes;
    private EyeofcloudClient eyeofcloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variationa);
        application = (MyApplication) getApplication();
        userName = application.getUserName();
        attributes = application.getAttributes();
        eyeofcloud = application.getEyeofcloudManager().getEyeofcloud();

        Button conversionButton = (Button)findViewById(R.id.button3);
        conversionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eyeofcloud.track("click", userName, attributes);
                Intent intent = new Intent(application.getBaseContext(), EventConfirmActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
