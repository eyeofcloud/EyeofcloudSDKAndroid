package com.example.eyeofcloud.androidfullstackactivateintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient
import com.eyeofcloud.ab.config.Variation

class HomeActivity : Activity() {
    private var application: MyApplication? = null
    private var userName: String? = null
    private var attributes: Map<String, String>? = null
    private var eyeofcloud: EyeofcloudClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        application = getApplication() as MyApplication
        userName = application!!.userName
        attributes = application!!.attributes
        eyeofcloud = application!!.eyeofcloudManager!!.eyeofcloud

        val openButton = findViewById<View>(R.id.button) as Button
        openButton.setOnClickListener {
            var intent = Intent(application!!.baseContext, DefaultActivity::class.java)
            val variation:Variation? = eyeofcloud!!.activate("Kotlin测试", userName!!, attributes!!)
            if (variation != null) {
                if (variation.key == "优化版本A") {
                    intent = Intent(application!!.baseContext, VariationAActivity::class.java)
                } else if (variation.key == "优化版本B") {
                    intent = Intent(application!!.baseContext, VariationBActivity::class.java)
                }
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
