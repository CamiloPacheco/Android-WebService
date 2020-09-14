package com.cacomas.navigationlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.repository.api.Post
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsAdapter(val posts: ArrayList<Post>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.title.text = post.title
            if (!post.completed)
                itemView.imageButton.setImageResource(R.drawable.cruz)
            else
                itemView.imageButton.setImageResource(R.drawable.chulo)


        }
    }
}