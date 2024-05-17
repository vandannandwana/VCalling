package com.example.vcalling.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.vcalling.R
import com.example.vcalling.presentation.viewmodel.UserProfileViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen(context: Context, innerPadding: PaddingValues, vModel: UserProfileViewModel, onLoginClick:()-> Unit, onNewUserClick:()-> Unit,onVerifyClick: () -> Unit) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding), contentAlignment = Alignment.Center) {
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
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(12.dp).fillMaxWidth(),
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
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.padding(12.dp).fillMaxWidth(),
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
                    Spacer(modifier = Modifier.height(12.dp))
                    Row (horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "google_logo",
                            modifier = Modifier.size(30.dp)
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.face),
                            contentDescription = "facebook_logo",
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    ElevatedButton(
                        onClick = {
                            if (email.isNotEmpty() && password.isNotEmpty()) {
                                vModel.login(email, password,onLoginClick)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please fill all the fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .padding(12.dp)

                    ) {

                        Text(text = "Login")

                    }

                }

            }

            Text(
                text = "Don't have an account? Sign Up",
                color = Color.Red,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        onNewUserClick()
                    }
            )

            PhoneVerification(onVerifyClick,auth = FirebaseAuth.getInstance(),activity = MainActivity())

        }

    }

}

@Composable
fun PhoneVerification(onVerifyClick: () -> Unit, auth: FirebaseAuth, activity: MainActivity) {
    var mobileNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var otpSent by remember { mutableStateOf(false) }
    var storedVerificationId by remember { mutableStateOf("") }
    var resendToken: PhoneAuthProvider.ForceResendingToken? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("Vandan", "Verification completed: ${credential.smsCode}")
            otpSent = true
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            Log.e("Vandan", "Verification failed: ${exception.message}", exception)
            Toast.makeText(context, "Verification failed: ${exception.message}", Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            storedVerificationId = verificationId
            resendToken = token
            Log.d("Vandan", "OTP sent successfully: $verificationId")
            otpSent = true
            Toast.makeText(context, "OTP sent successfully", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "OTP Verification")
        Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF365349),
                    unfocusedLabelColor = Color.Unspecified,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

        if (otpSent) {
            TextField(
                value = otp,
                onValueChange = { otp = it },
                label = { Text("OTP") },
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF365349),
                    unfocusedLabelColor = Color.Unspecified,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ))
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){

            ElevatedButton(onClick = {
                if (mobileNumber.isNotEmpty()) {
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(mobileNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(activity)
                        .setCallbacks(callbacks)
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                } else {
                    Toast.makeText(context, "Enter mobile number correctly", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = if (otpSent) "Resend OTP" else "Send OTP")
            }

            if (otpSent) {
                ElevatedButton(onClick = {
                    if (storedVerificationId.isNotEmpty() && otp.isNotEmpty()) {
                        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp)
                        signInWithPhoneAuthCredential(onVerifyClick,auth,activity,credential) { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Enter OTP", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Verify OTP")
                }
            }

        }
    }


}
fun signInWithPhoneAuthCredential(onVerifyClick: () -> Unit,auth: FirebaseAuth,activity: MainActivity,credential: PhoneAuthCredential, onComplete: (message: String) -> Unit) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                Log.d("Vandan", "signInWithCredential:success")
                val user = task.result?.user
                onVerifyClick()
                onComplete("Sign in success: ${user.toString()}")
            } else {
                Log.e("Vandan", "signInWithCredential:failure", task.exception)
                onComplete("Sign in failed: ${task.exception?.message}")
            }
        }
}