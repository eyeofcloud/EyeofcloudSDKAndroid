package com.example.eyeofcloud.androidfullstackparameter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.eyeofcloud.ab.android.sdk.EyeofcloudClient

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

        var backgroundColor = eyeofcloud!!.getVariableString("font_color", userName!!, attributes!!, true)
        if (backgroundColor == null) {
            backgroundColor = "#aaaaaa"
        }
        var title = eyeofcloud!!.getVariableString("home_title", userName!!, attributes!!, true)
        if (title == null) {
            title = "原始版本界面"
        }

        val textView = findViewById<View>(R.id.textView2) as TextView
        textView.text = title

        val conversionButton = findViewById<View>(R.id.button) as Button
        conversionButton.setBackgroundColor(Color.parseColor(backgroundColor))
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