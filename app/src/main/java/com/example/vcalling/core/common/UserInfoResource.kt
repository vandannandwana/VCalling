package com.example.vcalling.core.common

sealed class UserInfoResource<T>(val user:T?=null,val message:String?=null){

    class Success<T>(user:T):UserInfoResource<T>(user)
    class Error<T>(message:String,user:T?=null):UserInfoResource<T>(user,message)
    class Loading<T>:UserInfoResource<T>()

}