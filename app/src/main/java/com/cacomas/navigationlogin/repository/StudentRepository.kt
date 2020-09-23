package com.cacomas.navigationlogin.repository

import com.cacomas.navigationlogin.repository.api.StudentApiService

class StudentRepository {
    private val studentService =StudentApiService()
    fun addCourse(db_id:String, course_id:String,token:String) =studentService.addStudent(db_id,course_id,token)
    fun showStudent(db_id:String, student_id:String,token:String)=studentService.showStudent(db_id,student_id,token)
    fun getStudent() =studentService.getStudent()
}