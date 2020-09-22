package com.cacomas.navigationlogin.repository.api
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.CourseDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CourseApi {
    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header("Authorization") header: String): Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header("Authorization") header: String): Call<Course>

    @GET("{dbId}/courses/{courseId}")
    fun ShowCourseDetails(@Path("dbId") user: String,@Path("courseId") index: String, @Header("Authorization") header: String): Call<CourseDetails>

    @GET("{dbId}/restart")
    fun deleteCourses(@Path("dbId") user: String, @Header("Authorization") header: String): Call<ResponseBody>

}