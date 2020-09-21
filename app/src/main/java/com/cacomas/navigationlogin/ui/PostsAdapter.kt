package com.cacomas.navigationlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Course
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsAdapter(val posts: ArrayList<Course>, var clickListner: OnCourseItemClickListner): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(posts[position])
        holder.initialize(posts.get(position),clickListner)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //fun bind(course: Course) {
            //itemView.title.text = "Class : " +course.name
            //itemView.student.text=" Student : "+ course.students
            //itemView.professor.text=" Professor : "+ course.professor
        var title = itemView.title
        var student = itemView.student
        var professor = itemView.professor

            fun initialize(item: Course, action:OnCourseItemClickListner){
                itemView.title.text = item.name
                itemView.student.text = item.students
                itemView.professor.text = item.professor

                itemView.setOnClickListener{
                    action.onItemClick(item,adapterPosition)
                }

            }
        //}
    }
    interface OnCourseItemClickListner{
        fun onItemClick(item: Course, position: Int)
    }
}