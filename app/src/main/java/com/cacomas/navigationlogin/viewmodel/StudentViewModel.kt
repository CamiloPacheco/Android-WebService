package com.cacomas.navigationlogin.viewmodel


import androidx.lifecycle.ViewModel
import com.cacomas.navigationlogin.repository.StudentRepository

class StudentViewModel: ViewModel()  {
    private val repository = StudentRepository()
    fun addStudent(db_id:String, course_id:String,token:String) =repository.addCourse(db_id,course_id,token)
    fun showStudent(db_id:String, student_id:String,token:String)= repository.showStudent(db_id,student_id,token)
    fun showProfessor(db_id:String, professor_id:String,token:String)= repository.getProfessor(db_id,professor_id,token)
    fun getStudent()=repository.getStudent()
}