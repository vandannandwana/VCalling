package com.example.vcalling.presentation.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.vcalling.presentation.navigation.Navigation
import com.example.vcalling.ui.theme.VCallingTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VCallingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val currentUser = auth.currentUser != null
                    Navigation(lifecycleOwner = this,context = this, innerpadding = innerPadding, currentUser = currentUser)
                }
            }
        }
    }
}
