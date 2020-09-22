package com.cacomas.navigationlogin.repository.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.CourseDetails
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CourseApiService {
    companion object{

        val theResponse = MutableLiveData<List<Course>>()
        val responseCourseDetails = MutableLiveData<List<CourseDetails>>()
        var courses = mutableListOf<Course>()
        var courseDetails=mutableListOf<CourseDetails>()

        fun getRestEngine(): CourseApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(CourseApi::class.java)

        }
    }

    fun getCourseData() = theResponse
    fun getCourseDetails()= responseCourseDetails

    fun getCourses(user: String, token: String){

        //Log.d("MyOut", "getCourses with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine().getCourses(user,auth).enqueue(object: Callback<List<Course>>{
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        theResponse.postValue(response.body())
                        courses.clear()
                        val t = response.body() as List<Course>
                        courses.addAll(t)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

    }
    fun addCourse(user: String, token: String) {

        Log.d("MyOut", "addCourse with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine().addCourse(user,auth).enqueue(object: Callback<Course>{
            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //Log.d("MyOut", "OK isSuccessful token " )
                        courses.add(response.body()!!)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<Course>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
    fun ShowCourseDetails(user: String,Index: String,token: String) {

        Log.d("MyOut", "addCourse with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine().ShowCourseDetails(user,Index,auth).enqueue(object: Callback<CourseDetails>{
            override fun onResponse(call: Call<CourseDetails>, response: Response<CourseDetails>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val courseDetailsresponse = response.body()
                    if (courseDetailsresponse!= null) {
                        Log.d("MyOut", "Se obtuvieron los detalles del curso " )
                        courseDetails.clear()
                        courseDetails.add(response.body()!!)
                        responseCourseDetails.postValue(courseDetails)
                        Log.d("MyOut", "funciona" )
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<CourseDetails>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun deleteCourses(db_id:String,token:String) {
        val auth = "Bearer "+token
        CourseApiService.getRestEngine().deleteCourses(db_id,auth).enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body()?.string())
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        getCourses(db_id, token)
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
}