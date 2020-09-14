package com.cacomas.navigationlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.cacomas.navigationlogin.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository
    fun getLogged() = loginRepository.getLogged()
    fun setLogged(state: Boolean){
        loginRepository.setLogged(state)
    }
}