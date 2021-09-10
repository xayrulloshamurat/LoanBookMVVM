package com.example.loan_book_mvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModul = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
}
val viewModel = module{

}