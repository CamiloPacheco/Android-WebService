package com.cacomas.navigationlogin.repository.api
import com.cacomas.navigationlogin.data.Course
import retrofit2.Call
import retrofit2.http.*

interface CourseApi {
    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header("Authorization") header: String): Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header("Authorization") header: String): Call<Course>
}