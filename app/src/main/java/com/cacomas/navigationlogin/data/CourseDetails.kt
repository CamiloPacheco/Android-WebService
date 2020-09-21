package com.cacomas.navigationlogin.data


class CourseDetails (
    var name: String,
    val Professors:Professor,
    val Students:MutableList<Student>
)