package com.cacomas.navigationlogin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.util.PreferenceProvider

object SignUpRepository {
    private var userList = mutableListOf<User>()
    private val users = MutableLiveData<List<User>>()
    init {
        userList.add(User(PreferenceProvider.getUser()!!,PreferenceProvider.getPass()!!))
        users.value = userList
    }

    fun getUsers() = users as LiveData<List<User>>

    fun addUser(user: User) {
        userList.add(user)
        users.value = userList
        PreferenceProvider.setUser(user.name,user.pass)
    }
}