package com.cacomas.navigationlogin.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.cacomas.navigationlogin.repository.LoginRepository
import com.cacomas.navigationlogin.data.User
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var userLiveData = MutableLiveData<User>()
    var user: String = ""
    var token: String = ""

    private val repository = LoginRepository()

    fun getLogged() = repository.getLogged()

    fun setLogged(state: Boolean){
        repository.setLogged(state)
    }

    fun signIn(email: String, clave: String, usuario : String) =
        repository.signIn(User(email, clave, usuario, usuario,"",""))

    fun signUp(email: String, clave: String, usuario : String) =
        repository.signUp(User(email, clave, usuario, usuario,"",""))

    fun settoken(tokenFragment: String){
        token =  tokenFragment
        repository.setToken(token)
    }
    fun setuser(userFragment: String){
        user = userFragment
        repository.setUser(user)
    }

    fun getUsuario()= repository.getUser()

    fun gettoken()= repository.getToken()

    //fun getUser() = userLiveData
}