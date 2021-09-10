package com.example.loan_book_mvvm.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignUp

class SignUpViewModel : ViewModel(){
    private var auth = AuthHelperSignUp()
    var signUp : MutableLiveData<String> = MutableLiveData()
    fun signUp (email:String, password: String){
        signUp.value = "loading"
        auth.createUser(email, password, AuthResult = {
            signUp.value = "success"
        }, Exception = {
            it.message
        })
    }

}