package com.cacomas.navigationlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.CourseDetails
import kotlinx.coroutines.launch
import com.cacomas.navigationlogin.repository.PostRepository

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    val postsLiveData = MutableLiveData<List<Course>>()
    val courseDetails=MutableLiveData<List<CourseDetails>>()

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
    fun ShowCourseDetails(user: String,Index: String,token: String){
        repository.ShowCourseDetails(user,Index,token)
    }
    fun getCourseDetails(){
        val Details=repository.getCourseDetails()
        courseDetails.postValue(Details)
    }
}