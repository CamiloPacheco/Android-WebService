package com.cacomas.navigationlogin.util

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.User

class PreferenceProvider {
    companion object {

        lateinit var preference : SharedPreferences
        fun initialize(context: Context) {
            preference = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
        }

        fun setValue(value: Boolean){
            preference.edit().putBoolean("key",value).apply()
        }
        fun getValue(): Boolean? {
            return preference.getBoolean("key",false)
        }
        fun setUser(user: String){
            preference.edit().putString("user",user).apply()

        }
        fun setToken(token: String){
            preference.edit().putString("token",token).apply()
        }

        fun getUser(): String? {
            return preference.getString("user", "")
        }
        fun getToken(): String? {
            return preference.getString("token", "")
        }
    }
}