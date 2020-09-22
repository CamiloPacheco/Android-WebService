package com.cacomas.navigationlogin.repository.api

import com.cacomas.navigationlogin.data.Course
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApi {
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String,@Field("courseId") courseId: String, @Header("Authorization") header: String): Call<ResponseBody>
}