package com.cacomas.navigationlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.CourseDetails
import com.cacomas.navigationlogin.data.Student
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.list_item_post.view.*
import kotlinx.android.synthetic.main.list_item_students.view.*

class StudentListAdapter(val students: ArrayList<Student>, var StudentclickListner: OnStudentItemClickListner): RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_students, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(posts[position])
        holder.initialize(students.get(position),StudentclickListner)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //fun bind(course: Course) {
        //itemView.title.text = "Class : " +course.name
        //itemView.student.text=" Student : "+ course.students
        //itemView.professor.text=" Professor : "+ course.professor
        var name= itemView.StudentNameTxt
        var email = itemView.StudentEmailTxt


        fun initialize(item: Student, action:OnStudentItemClickListner){
            itemView.StudentNameTxt.text = item.name
            itemView.StudentEmailTxt.text = item.email


            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }
        //}
    }
    interface OnStudentItemClickListner{
        fun onItemClick(item: Student, position: Int)
    }
}