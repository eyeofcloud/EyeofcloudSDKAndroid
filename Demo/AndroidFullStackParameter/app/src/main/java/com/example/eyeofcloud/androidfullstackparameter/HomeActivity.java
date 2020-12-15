package com.example.eyeofcloud.androidfullstackparameter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient;

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

        String backgroundColor = eyeofcloud.getVariableString("home-background-color", userName, attributes, true);
        if (backgroundColor == null) {
            backgroundColor = "#aaaaaa";
        }
        String title = eyeofcloud.getVariableString("home-title", userName, attributes, true);
        if (title == null) {
            title = "原始版本界面";
        }

        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText(title);

        Button conversionButton = (Button)findViewById(R.id.button);
        conversionButton.setBackgroundColor(Color.parseColor(backgroundColor));
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