package com.example.loan_book_mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.helper.DataHelper

class MainViewModel(private val users : DataHelper) : ViewModel() {
    var usersLive: MutableLiveData<String> = MutableLiveData()
    fun debtFunc(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
    ) {
        usersLive.value = "loading"
        users.addDebt(name, amount, comments, date, onSuccesListener={
            usersLive.value = "success"
        }, onFailureListener={
            usersLive.value = it
        })
    }
    fun loanFunc(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
    ) {
        usersLive.value = "loading"
        users.addLoan(name, amount, comments, date, onSuccesListener={
            usersLive.value = "success"
        }, onFailureListener={
            usersLive.value = it
        })
    }
    fun recycler(){
        users.eventChangeListener(

        )
    }
}