package com.example.eyeofcloud.androidfullstackparameter

import android.app.Application
import android.content.Context


import com.eyeofcloud.ab.android.sdk.EyeofcloudManager

import java.util.HashMap
import java.util.UUID

class MyApplication : Application() {
    var eyeofcloudManager: EyeofcloudManager? = null
        private set

    val attributes: Map<String, String>
        get() {
            val attributes = HashMap<String, String>()
            attributes["gender"] = "male"
            return attributes
        }

    val userName: String
        get() {
            val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
            var name = sharedPreferences.getString("userName", null)
            if (name == null) {
                name = UUID.randomUUID().toString()
                sharedPreferences.edit().putString("userName", name).apply()
            }
            return name
        }

    override fun onCreate() {
        super.onCreate()
        eyeofcloudManager = EyeofcloudManager.builder(PROJECT_ID).build()

        // for on-premise deployment, need to set config file url
        //eyeofcloudManager!!.setConfigUrlFormat("https://yourdomain/eyeofcloud/editor/config/json/fullstack/641_4f26904d4443d2d24112fe44784cb74c4.json")
    }

    companion object {

        const val PROJECT_ID = "641_4f26904d4443d2d24112fe44784cb74c"
    }
}