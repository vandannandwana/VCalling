package com.example.vcalling.data.model

data class UserInfo(
    val name: String,
    val password: String,
    val age: String,
    val phone: String,
    val email: String,
){
   constructor() : this("","","","","")
}
