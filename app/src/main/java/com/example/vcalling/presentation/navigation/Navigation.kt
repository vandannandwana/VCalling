package com.example.vcalling.presentation.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vcalling.presentation.screens.HomeScreen
import com.example.vcalling.presentation.screens.LoginScreen
import com.example.vcalling.presentation.screens.SignUpScreen
import com.example.vcalling.presentation.viewmodel.UserProfileViewModel

@Composable
fun Navigation(
//    lifecycleOwner: LifecycleOwner,
//                 activity: MainActivity,
                 context: Context,
                 innerpadding: PaddingValues,
                 currentUser: Boolean)
{

    val userViewModel = hiltViewModel<UserProfileViewModel>()
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = if (!currentUser) NavScreens.LoginScreen.route else NavScreens.HomeScreen.route
    ) {

        composable(NavScreens.LoginScreen.route) {
            LoginScreen(
                context = context,
                innerPadding = innerpadding,
                vModel = userViewModel,
                onLoginClick = { navigationController.navigate(NavScreens.HomeScreen.route);Log.d("Vandan_LOGIN",it.toString())},
                onNewUserClick = { navigationController.popBackStack();navigationController.navigate(NavScreens.SignupScreen.route) },
                onVerifyClick = {navigationController.navigate(NavScreens.HomeScreen.route)}
            )
        }

        composable(NavScreens.SignupScreen.route){

            SignUpScreen(
                context = context,
                vModel = userViewModel,
                onOldUserClick = { navigationController.popBackStack();navigationController.navigate(NavScreens.LoginScreen.route) }
            )

        }

        composable(NavScreens.HomeScreen.route) {

            HomeScreen(
                context = context,
                viewModel = userViewModel,
                onLogoutClick = { navigationController.navigate(NavScreens.LoginScreen.route) }
            )

        }


    }



}