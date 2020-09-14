package com.cacomas.navigationlogin.data

data class User (val name: String, val pass: String) {

    override fun toString(): String {
        return "$name"+"$pass"
    }

}