package com.cacomas.navigationlogin.repository.api
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostsApi {
    @GET("todos/")
    suspend fun getPosts(): List<Post>

    @GET("todos/{index}/")
    suspend fun getPost(@Path("index") index: Int): Post

    @POST("todos/")
    suspend fun postAPost(@Body post: Post): Post
}