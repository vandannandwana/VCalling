package com.example.vcalling.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.vcalling.R
import com.example.vcalling.presentation.viewmodel.UserProfileViewModel

@Composable
fun SignUpScreen(context: Context, vModel: UserProfileViewModel, onOldUserClick: () -> Unit) {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var phone by rememberSaveable {
        mutableStateOf("")
    }

    var age by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }
    var password2 by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.mylogo),
                    contentDescription = "mylogo",
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .size(180.dp)

                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xBE737471)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeholder = { Text(text = "Enter Name") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )

                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Enter Email") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = phone,
                        onValueChange = { phone = it },
                        modifier = Modifier.padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Enter Phone Number") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = age,
                        onValueChange = { age = it },
                        modifier = Modifier.padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Enter Age") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.padding(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text(text = "Enter Password") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = password2,
                        onValueChange = { password2 = it },
                        modifier = Modifier.padding(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text(text = "Enter Password Again") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )



                    Text(
                        text = "Already have an account? Sign In",
                        color = Color.Blue,
                        modifier = Modifier.padding(12.dp)
                            .clickable {
                                onOldUserClick()
                            }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ElevatedButton(
                            onClick = {

                                if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                                    if(password == password2){
                                        vModel.signup(email,name,age,phone,password)
                                    }else{
                                        Toast.makeText(context, "Password do not match", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                                }

                            },
                            modifier = Modifier.padding(12.dp)

                        ) {

                            Text(text = "SignUp")

                        }
                    }

                }

            }

        }

    }

}