package com.cacomas.navigationlogin.repository.api


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface StudentApi {
    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String,@Field("courseId") courseId: String, @Header("Authorization") header: String): Call<ResponseBody>
}