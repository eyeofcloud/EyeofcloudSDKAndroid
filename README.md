# Eyeofcloud Android SDK

This repository houses the Android SDK for use with Eyeofcloud Full Stack.

Eyeofcloud Full Stack is A/B testing and feature flag management for product development teams.


## Getting Started


### Requirements
* Android API 14 or higher 


### Installing the SDK
To add the android-sdk and all modules to your project, include the following in your app's `build.gradle`:

```
repositories {
        maven { url 'https://dl.bintray.com/eyeofcloud/eyeofcloud' }
}

dependencies {
       implementation 'com.eyeofcloud.ab:android.sdk:1.4.16' 
}
```


### Samples
A sample code for SDK initialization and experiments:

```
EyeofcloudManager eyeofcloudManager = EyeofcloudManager.builder("8_3e9f2898e924f4961afb27b57ee7da08").build();
eyeofcloudManager.initialize(this, new EyeofcloudStartListener() {

    @Override
    public void onStart(EyeofcloudClient eyeofcloudClient) {
        Variation variation = eyeofcloudManager.getEyeofcloud().activate("experiment","id");
    }
});

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        eyeofcloudManager.getEyeofcloud().track("goal","id");
    }
});

```




