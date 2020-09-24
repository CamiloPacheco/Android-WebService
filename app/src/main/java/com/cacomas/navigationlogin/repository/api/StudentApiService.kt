package com.cacomas.navigationlogin.repository.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.StudentDetails
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentApiService {
    private val courseApiService = CourseApiService()
    val studentDetails = MutableLiveData<StudentDetails>()
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
    fun getStudent() = studentDetails
    fun addStudent(db_id:String, course_id:String,token:String) {
        val auth = "Bearer "+token
        getRestEngine().addStudent(db_id,course_id,auth).enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body()?.string())
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        courseApiService.ShowCourseDetails(db_id,course_id,token)
                    }
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

    fun showStudent(db_id:String, student_id:String,token:String) {
        val auth = "Bearer "+token
        getRestEngine().showStudent(db_id,student_id,auth).enqueue(object:
            Callback<StudentDetails> {
            override fun onResponse(call: Call<StudentDetails>, response: Response<StudentDetails>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "student details add" )
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        studentDetails.postValue(loginResponse)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    // Log.d("MyOut", "NOK isNotSuccessful " + response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<StudentDetails>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun showProfessor(db_id:String, professor_id:String,token:String) {
        val auth = "Bearer "+token
        getRestEngine().showProfesor(db_id,professor_id,auth).enqueue(object:
            Callback<StudentDetails> {
            override fun onResponse(call: Call<StudentDetails>, response: Response<StudentDetails>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "student details add" )
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        studentDetails.postValue(loginResponse)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    // Log.d("MyOut", "NOK isNotSuccessful " + response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<StudentDetails>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
}