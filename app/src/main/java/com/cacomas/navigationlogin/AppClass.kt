package com.cacomas.navigationlogin


import android.app.Application
import android.util.Log
import com.cacomas.navigationlogin.util.PreferenceProvider

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("VideoViewModel","AppClass onCreate")
        PreferenceProvider.initialize(this)
    }
}
