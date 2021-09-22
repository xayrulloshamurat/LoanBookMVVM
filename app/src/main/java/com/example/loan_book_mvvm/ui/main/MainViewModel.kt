package com.example.loan_book_mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.helper.DataHelper

class MainViewModel(private val users : DataHelper) : ViewModel() {
    var usersLive: MutableLiveData<String> = MutableLiveData()
    fun usersFunc(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: Exception) -> Unit
    ) {
        usersLive.value = "loading"
        users.addDebt(name, amount, comments, date, onSuccesListener={
            usersLive.value = "success"
        }, onFailureListener={
            usersLive.value = it
        })
    }
}