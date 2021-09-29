package com.example.loan_book_mvvm.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.data.helper.AuthHelperSignUp

class SignUpViewModel(private val auth : AuthHelperSignUp) : ViewModel(){
    var signUp : MutableLiveData<String> = MutableLiveData()
    fun signUp (email:String, password: String){
        signUp.value = "loading"
        auth.createUser(email, password, AuthResult = {
            signUp.value = "success"
        }, Exception = {
            signUp.value = it.message
        })
    }

}