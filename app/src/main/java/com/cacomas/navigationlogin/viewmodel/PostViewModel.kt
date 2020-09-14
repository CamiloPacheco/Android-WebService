package com.cacomas.navigationlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import com.cacomas.navigationlogin.repository.PostRepository
import com.cacomas.navigationlogin.repository.api.Post
class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    val posts = mutableListOf<Post>()
    val postsLiveData = MutableLiveData<List<Post>>()

    init {
        getPost()
    }
    private fun getPosts() {
        viewModelScope.launch {
            posts.addAll(repository.getPosts())
            postsLiveData.postValue(posts)
        }
    }


    fun getPost() {
        viewModelScope.launch {
            val post = repository.getPost(posts.size+1)
            posts.add(post)
            postsLiveData.postValue(posts)
        }
    }

}