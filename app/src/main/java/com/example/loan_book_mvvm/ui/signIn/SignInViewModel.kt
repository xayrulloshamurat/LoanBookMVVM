package com.example.loan_book_mvvm.ui.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignIn

class SignInViewModel : ViewModel() {
    var auth = AuthHelperSignIn()
    var signIn: MutableLiveData<String> = MutableLiveData()
    fun signIn(email:String, password:String){
        signIn.value = "loading"
        auth.signIn(email,password,AuthResult = {
            signIn.value = "success"
        },Exception = {
            signIn.value = it.message
        })
    }
}