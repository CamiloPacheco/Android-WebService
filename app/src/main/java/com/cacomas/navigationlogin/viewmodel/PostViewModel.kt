package com.cacomas.navigationlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.Course
import kotlinx.coroutines.launch
import com.cacomas.navigationlogin.repository.PostRepository

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    val posts = mutableListOf<Course>()
    val postsLiveData = MutableLiveData<List<Course>>()

    init {
        //getPost()
    }

    fun getCourses(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.getCourses(user, token)
    }
    fun getCoursesData()  {
        val post = repository.getCoursesData()
        postsLiveData.postValue(post)
    }
    fun addCourses(user: String, token: String)  {
        repository.addCourses(user, token)
    }
}