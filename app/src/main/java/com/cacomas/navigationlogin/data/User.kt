package com.cacomas.navigationlogin.data

data class User  (
    var email: String = "",
    var password: String = "",
    var username: String = "",
    var name: String = "",
    var token: String = "",
    var type: String = "",
) {
    var error: String = ""
}