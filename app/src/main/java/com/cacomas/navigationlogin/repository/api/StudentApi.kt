package com.cacomas.navigationlogin.repository.api


import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.StudentDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface StudentApi {
    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String,@Field("courseId") courseId: String, @Header("Authorization") header: String): Call<ResponseBody>

    @GET("{dbId}/students/{studentId}")
    fun showStudent(@Path("dbId") user: String,@Path("studentId") studentID: String, @Header("Authorization") header: String): Call<StudentDetails>
}