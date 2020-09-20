package com.cacomas.navigationlogin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.repository.api.LoginApiService
import com.cacomas.navigationlogin.util.PreferenceProvider

object SignUpRepository {
    private var userList = mutableListOf<User>()
    private val users = MutableLiveData<List<User>>()
    /**init {
        userList.add(User(PreferenceProvider.getUser()!!,PreferenceProvider.getPass()!!))
        users.value = userList
    }*/

    var userLiveData = MutableLiveData<User>()

    private val service = LoginApiService()

    fun signUp(user: User) = service.signUp(user)

    fun getUser() = userLiveData as LiveData<User>
}