package com.cacomas.navigationlogin.viewmodel


import androidx.lifecycle.ViewModel
import com.cacomas.navigationlogin.repository.StudentRepository

class StudentViewModel: ViewModel()  {
    private val repository = StudentRepository()
    fun addStudent(db_id:String, course_id:String,token:String) =repository.addCourse(db_id,course_id,token)
}