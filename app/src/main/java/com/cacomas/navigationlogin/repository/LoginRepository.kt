package com.cacomas.navigationlogin.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.util.PreferenceProvider
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.repository.api.LoginApiService


class LoginRepository {
    var logged = MutableLiveData<Boolean>()
    var stateLogged : Boolean = false

    var userLiveData = MutableLiveData<User>()

    private val service = LoginApiService()

    fun signIn(user: User) = service.signIn(user)

    fun signUp(user: User) = service.signUp(user)

    fun getUser() = userLiveData as LiveData<User>

    init {
        stateLogged = PreferenceProvider.getValue()!!
        logged.value = stateLogged;
    }
    fun getLogged() = logged as LiveData<Boolean>
    fun setLogged(state: Boolean){
        stateLogged = state
        logged.value = stateLogged;
        PreferenceProvider.setValue(state)
    }
}