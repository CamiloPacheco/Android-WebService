package com.cacomas.navigationlogin.data


class CourseDetails (
    var name: String,
    val professor:Professor,
    val students:MutableList<Student>
)