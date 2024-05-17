package com.example.vcalling.presentation.states

import com.example.vcalling.data.model.UserInfo

data class UserInfoState(

    val user: UserInfo? = UserInfo("","","","",""),
    val errorMessage:String? = "",
    val isLoading:Boolean = false

)