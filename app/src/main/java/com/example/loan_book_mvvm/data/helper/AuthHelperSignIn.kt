package com.example.loan_book_mvvm.data.helper

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthHelperSignIn( private val auth : FirebaseAuth) {


    fun signIn(
        email: String,
        password: String,
        AuthResult: (it: AuthResult) -> Unit,
        Exception: (it: Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                AuthResult.invoke(it)
            }
            .addOnFailureListener {
                Exception.invoke(it)
            }
    }
}