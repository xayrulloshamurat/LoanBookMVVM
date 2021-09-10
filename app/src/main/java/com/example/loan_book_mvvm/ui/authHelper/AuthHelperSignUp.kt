package com.example.loan_book_mvvm.ui.authHelper

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class AuthHelperSignUp {
    private val auth = FirebaseAuth.getInstance()

    fun createUser(
        email: String,
        password: String,
        AuthResult: (it: AuthResult) -> Unit,
        Exception: (it: Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            AuthResult.invoke(it)
        }
            .addOnFailureListener {
                Exception.invoke(it)
            }
    }
}