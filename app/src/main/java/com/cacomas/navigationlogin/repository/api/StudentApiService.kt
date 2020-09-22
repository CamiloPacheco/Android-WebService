package com.cacomas.navigationlogin.repository.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.CourseDetails
import com.cacomas.navigationlogin.data.StudentCreated
import com.cacomas.navigationlogin.data.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentApiService {

    companion object{

        fun getRestEngine(): StudentApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(StudentApi::class.java)

        }
    }
    fun addStudent(db_id:String, course_id:String,token:String) {
        val auth = "Bearer "+token
        getRestEngine().addStudent(db_id,course_id,auth).enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body()?.string())
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    // Log.d("MyOut", "NOK isNotSuccessful " + response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
}