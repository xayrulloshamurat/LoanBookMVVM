package com.example.loan_book_mvvm.di

import com.example.loan_book_mvvm.helper.AuthHelperSignIn
import com.example.loan_book_mvvm.helper.AuthHelperSignUp
import com.example.loan_book_mvvm.ui.signIn.SignInViewModel
import com.example.loan_book_mvvm.ui.signUp.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModul = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
}
val helperModul = module {
    single { AuthHelperSignIn(get()) }
    single { AuthHelperSignUp(get()) }
}
val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}