package com.example.vcalling.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.vcalling.R
import com.example.vcalling.data.model.UserInfo
import com.example.vcalling.presentation.viewmodel.UserProfileViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: UserProfileViewModel,
    onLogoutClick: () -> Unit
) {


    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var userState by remember {
                mutableStateOf(UserInfo())
            }

            LaunchedEffect(key1 = userState, key2 = viewModel) {
                viewModel.userinfo.observe(lifecycleOwner){user->
                    userState = user
                }
            }


            Profile(context,userState,viewModel)

            Button(onClick = { viewModel.logout(); onLogoutClick() }) {

                Text(text = "Logout")

            }

        }
    }


}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Profile(context: Context, user: UserInfo, viewModel: UserProfileViewModel) {

    var onEdit by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .aspectRatio(1.4f)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0x68FF0033), Color(0x370088FF)
                        ),
                        start = Offset(0.2f, 0.4f),
                        end = Offset(0.4f, 0.2f)
                    )
                ), contentAlignment = Alignment.Center
        ) {

            Box(modifier = Modifier.clip(RoundedCornerShape(100.dp))) {
                Image(
                    painter = painterResource(id = R.drawable.mylogo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.size(170.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.Edit,
                tint = if (onEdit) Color.Red else Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    )
                    .size(37.dp)
                    .padding(2.dp)
                    .clickable {
                        onEdit = !onEdit
                        Toast
                            .makeText(
                                context,
                                "Edit Profile Mode" + if (onEdit) "ON" else "OFF",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                contentDescription = "Edit Profile"
            )

        }

        Text(
            text = user.name,
            fontWeight = FontWeight.Black,
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Followers",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "4M",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Following",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "200K",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Connections",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "100K",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        
        Text(text = if(onEdit)"Editing Mode" else "Safe Mode",fontSize = 20.sp, fontWeight = FontWeight.Black, color = if(onEdit) Color.Red else Color.Green, modifier = Modifier.padding(12.dp))

        Spacer(modifier = Modifier.height(24.dp))


        var name by rememberSaveable {
            mutableStateOf("")
        }

        var email by rememberSaveable {
            mutableStateOf("")
        }

        var phone by rememberSaveable {
            mutableStateOf("")
        }

        var age by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }

        LaunchedEffect(user) {
            name = user.name
            email = user.email
            phone = user.phone
            age = user.age
            password = user.password
        }

        TextField(
            value = name,
            onValueChange = { name = it },
            enabled = onEdit,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Enter Name") },
            label = { Text(text = "Your Name") },
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
            enabled = onEdit,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(text = "Enter Email") },
            label = { Text(text = "Your Email") },
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
            enabled = onEdit,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(text = "Enter Phone Number") },
            label = { Text(text = "Your Phone Number") },
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
            enabled = onEdit,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(text = "Enter Age") },
            label = { Text(text = "Your Age") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF365349),
                unfocusedLabelColor = Color.Unspecified,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Button(onClick = {onEdit = false;viewModel.updateUserInfo(name = name, password = password, email = email, phone = phone, age = age);viewModel.getUser(viewModel.getUid()!!)}, enabled = onEdit) {
            Text(text = "Update Details")
        }


    }

}