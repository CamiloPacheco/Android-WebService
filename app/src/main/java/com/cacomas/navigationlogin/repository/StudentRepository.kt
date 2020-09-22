package com.cacomas.navigationlogin.repository

import com.cacomas.navigationlogin.repository.api.StudentApiService

class StudentRepository {
    private val studentService =StudentApiService()
    fun addCourse(db_id:String, course_id:String,token:String) =studentService.addStudent(db_id,course_id,token)

}