package com.example.loan_book_mvvm.ui.authHelper

import android.view.View
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthHelperSignIn {
    private val auth = FirebaseAuth.getInstance()

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