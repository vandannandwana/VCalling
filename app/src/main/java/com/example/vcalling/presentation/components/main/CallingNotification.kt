package com.example.vcalling.presentation.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun CallingNotification (){

    Card {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

            Text(modifier = Modifier.padding(10.dp),text = "Here's a call for you !!")
            Row {

                Button(modifier = Modifier.padding(10.dp),onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                    Text(text = "Answer")
                }


                Button(modifier = Modifier.padding(10.dp),onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text(text = "Decline")
                }

            }

        }
    }

}