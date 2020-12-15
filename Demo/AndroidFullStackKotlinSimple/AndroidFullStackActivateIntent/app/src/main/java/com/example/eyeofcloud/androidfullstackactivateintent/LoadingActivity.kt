package com.example.eyeofcloud.androidfullstackactivateintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle



class LoadingActivity : Activity() {

    private var application: MyApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        application = getApplication() as MyApplication
    }

    override fun onStart() {
        super.onStart()

        application!!.eyeofcloudManager!!.initialize(this) {
            //sleep for showing loading UI
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val intent = Intent(application!!.baseContext, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
