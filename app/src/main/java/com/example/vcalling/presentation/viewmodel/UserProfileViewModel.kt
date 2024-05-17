package com.example.vcalling.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcalling.core.common.UserInfoResource
import com.example.vcalling.data.model.UserInfo
import com.example.vcalling.presentation.states.UserInfoState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val auth: FirebaseAuth, private val userDatabase: CollectionReference):ViewModel() {

    private val _userinfo = MutableStateFlow(UserInfoState())
    val userinfo: StateFlow<UserInfoState>
        get() = _userinfo

    init {

        if(auth.currentUser!=null){
            getUser(auth.currentUser!!.uid)
        }

    }

    fun getUid():String{
        return auth.currentUser!!.uid.toString()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateUserInfo(name:String, email:String, password:String, age:String, phone:String){
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "age" to age,
            "phone" to phone,
        )
        GlobalScope.launch {
            userDatabase.document(auth.currentUser!!.uid).set(user).await()
        }

    }






    fun logout()= auth.signOut()

    fun login(email: String,password: String,onLogin:()->Unit){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("Vandan_Login","Login Successfull")
                onLogin()
                return@addOnCompleteListener
            }else{
                Log.d("Vandan_Login",task.exception?.localizedMessage.toString())
                return@addOnCompleteListener
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun signup(email: String, name:String,age:String,phone:String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task->

            if(task.isSuccessful){

                Log.d("Vandan_Signup","Signup Successfull")

                val uid =auth.currentUser?.uid!!

                val user = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "password" to password,
                    "age" to age,
                    "phone" to phone,
                )
                GlobalScope.launch {
                    userDatabase.document(uid).set(user).await()
                }

            }else{
                Log.d("Vandan_Signup",task.exception?.localizedMessage.toString())
            }

        }

    }


    private fun getUserInfo(uid:String): Flow<UserInfoResource<UserInfo?>> = flow{

        Log.d("Vandan_UserInfo","Getting User form getUserInfo")

        emit(UserInfoResource.Loading())
        Log.d("Vandan_UserInfo",uid)
        val user = userDatabase.document(uid).get().await().toObject(UserInfo::class.java)
        Log.d("Vandan_UserInfo",user.toString())
        emit(UserInfoResource.Success(user))

    }.flowOn(Dispatchers.IO)
        .catch {
            emit(UserInfoResource.Error(it.message.toString()))
        }

    fun getUser(uid:String){
        Log.d("Vandan_UserInfo","Getting User")
        viewModelScope.launch {
            getUserInfo(uid).collect{
                when(it){

                    is UserInfoResource.Success->{

                        _userinfo.value = UserInfoState(user = it.user)
                        Log.d("Vandan_UserInfo",it.user.toString())

                    }

                    is UserInfoResource.Error->{

                        _userinfo.value = UserInfoState(errorMessage = it.message)

                    }

                    is UserInfoResource.Loading->{

                        _userinfo.value = UserInfoState(isLoading = true)

                    }

                }
            }
        }

    }
}