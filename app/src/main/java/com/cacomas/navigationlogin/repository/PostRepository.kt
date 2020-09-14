package com.cacomas.navigationlogin.repository

import com.cacomas.navigationlogin.repository.api.PostsApiService
import com.cacomas.navigationlogin.repository.api.Post

class PostRepository {
    private val apiService = PostsApiService()

    suspend fun getPosts() = apiService.getPosts()

    suspend fun getPost(index : Int) = apiService.getPost(index)

    suspend fun postAPost(post: Post) = apiService.postAPost(post)
}