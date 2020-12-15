package com.example.eyeofcloud.androidfullstackactivateintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient

class DefaultActivity : Activity() {

    private var application: MyApplication? = null
    private var userName: String? = null
    private var attributes: Map<String, String>? = null
    private var eyeofcloud: EyeofcloudClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        application = getApplication() as MyApplication
        userName = application!!.userName
        attributes = application!!.attributes
        eyeofcloud = application!!.eyeofcloudManager!!.eyeofcloud

        val conversionButton = findViewById<View>(R.id.button2) as Button
        conversionButton.setOnClickListener {
            eyeofcloud!!.track("click", userName!!, attributes!!)
            val intent = Intent(application!!.baseContext, EventConfirmActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}