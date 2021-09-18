package com.example.loan_book_mvvm.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.CalendarBinding
import com.example.loan_book_mvvm.databinding.DialogAddBinding

class CalendarDialog(nContext: Context): Dialog(nContext) {
    private lateinit var binding: CalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar)
        binding = CalendarBinding.inflate(layoutInflater)
    }
}