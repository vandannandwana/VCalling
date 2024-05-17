package com.example.vcalling.presentation.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ScreenShare
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun VideoCallScreen() {

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        }
    ) {

        Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center){
            Text(text = "VideoCall Screen", modifier = Modifier.padding(it))
        }

    }

}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                modifier = Modifier.size(12.dp),
                tint = Color.Red,
                contentDescription = "vc_time"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "00:01")
        }
        Text(text = "Calling With Vandan", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun BottomBar() {

    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Icon(imageVector = Icons.Default.Call, contentDescription = "call")
        Icon(imageVector = Icons.Default.MicOff, contentDescription = "call")
        Icon(imageVector = Icons.Default.VideocamOff, contentDescription = "call")
        Icon(imageVector = Icons.Default.Cameraswitch, contentDescription = "call")
        Icon(imageVector = Icons.AutoMirrored.Filled.ScreenShare, contentDescription = "call")

    }

}
