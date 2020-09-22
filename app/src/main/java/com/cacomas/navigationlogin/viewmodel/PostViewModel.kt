package com.cacomas.navigationlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

import com.cacomas.navigationlogin.repository.PostRepository

class PostViewModel : ViewModel() {
    private val repository = PostRepository()


    init {

    }

    fun getCourses(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.getCourses(user, token)
    }
    fun getCourseData()= repository.getCourseData()

    fun addCourses(user: String, token: String)  {
        repository.addCourses(user, token)
    }
    fun ShowCourseDetails(user: String,Index: String,token: String){
        repository.ShowCourseDetails(user,Index,token)
    }

    fun getCourseDetails() = repository.getCourseDetails()

    fun deleteCourses(user: String, token: String) = repository.deleteCourses(user, token)


}