package com.cacomas.navigationlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.repository.SignUpRepository

class SignUpViewModel: ViewModel() {
    private val singupRepository = SignUpRepository
    fun getUsers() = singupRepository.getUsers()
    fun addUser(user: User) = singupRepository.addUser(user)
}