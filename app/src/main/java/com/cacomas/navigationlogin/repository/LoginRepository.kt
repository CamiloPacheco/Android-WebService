package com.cacomas.navigationlogin.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.util.PreferenceProvider
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.repository.api.LoginApiService


class LoginRepository {
    var logged = MutableLiveData<Boolean>()
    var stateLogged : Boolean = false
    var stateUser : String =""
    var stateToken : String =""
    var userLiveData = MutableLiveData<String>()
    var tokenLiveData = MutableLiveData<String>()

    private val service = LoginApiService()

    fun signIn(user: User) = service.signIn(user)

    fun signUp(user: User) = service.signUp(user)

    fun getUser() = userLiveData as LiveData<String>
    fun getToken()=tokenLiveData as LiveData<String>

    fun setUser(user: String){
        stateUser = user
        userLiveData.value = stateUser;
        PreferenceProvider.setUser(user)
    }

    fun setToken(token: String){
        stateToken = token
        tokenLiveData.value = stateToken;
        PreferenceProvider.setToken(token)
    }




    init {
        stateLogged = PreferenceProvider.getValue()!!
        stateToken=PreferenceProvider.getToken()!!
        stateUser=PreferenceProvider.getUser()!!
        logged.value = stateLogged;
        tokenLiveData.value = stateToken;
        userLiveData.value = stateUser;

    }


    fun getLogged() = logged as LiveData<Boolean>
    fun setLogged(state: Boolean){
        stateLogged = state
        logged.value = stateLogged;
        PreferenceProvider.setValue(state)
    }
}