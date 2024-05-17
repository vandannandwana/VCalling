package com.example.vcalling.presentation.navigation

sealed class NavScreens(val route:String) {

    data object HomeScreen:NavScreens("home")
    data object LoginScreen:NavScreens("login")
    data object SignupScreen:NavScreens("signup")

}