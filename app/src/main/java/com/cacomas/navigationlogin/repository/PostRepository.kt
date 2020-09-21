package com.cacomas.navigationlogin.repository

import com.cacomas.navigationlogin.repository.api.CourseApiService


class PostRepository {
    private val service = CourseApiService()
    fun getCourses(user: String, token: String) = service.getCourses(user,token)
    fun getCoursesData() = service.getCourseData()
    fun addCourses(user: String, token: String) = service.addCourse(user,token)
    fun ShowCourseDetails(user: String,Index: String,token: String)=service.ShowCourseDetails(user,Index,token)
    fun getCourseDetails()=service.getCourseDetails()

}