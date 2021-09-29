package com.example.loan_book_mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loan_book_mvvm.data.Resource
import com.example.loan_book_mvvm.data.helper.Contacts
import com.example.loan_book_mvvm.data.helper.DataHelper

class MainViewModel(private val dataHelper : DataHelper) : ViewModel() {
    var transactionsLive: MutableLiveData<String> = MutableLiveData()
    val contactsLive : MutableLiveData<Resource<List<Contacts>>> = MutableLiveData()
    fun debtFunc(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
    ) {
        transactionsLive.value = "loading"
        dataHelper.addDebt(name, amount, comments, date, onSuccesListener={
            transactionsLive.value = "success"
        }, onFailureListener={
            transactionsLive.value = it
        })
    }
    fun loanFunc(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
    ) {
        transactionsLive.value = "loading"
        dataHelper.addLoan(name, amount, comments, date, onSuccesListener={
            transactionsLive.value = "success"
        }, onFailureListener={
            transactionsLive.value = it
        })
    }
    fun contactsFun(){
        contactsLive.value = Resource.loading()
        dataHelper.eventChangeListener(
            {
                 contactsLive.value = Resource.success(it)
            },
            {
                contactsLive.value = Resource.error(it)
            }
        )
    }

}