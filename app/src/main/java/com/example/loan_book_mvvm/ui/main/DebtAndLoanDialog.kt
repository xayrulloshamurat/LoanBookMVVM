package com.example.loan_book_mvvm.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.DialogAddBinding

class DebtAndLoanDialog(nContext: Context): Dialog(nContext) {
    private lateinit var calendar : CalendarDialog
    private lateinit var binding: DialogAddBinding
    
    var nContext = nContext
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add)
        binding = DialogAddBinding.inflate(layoutInflater)

        binding.calendar.setOnClickListener {
            Toast.makeText(nContext, "Ayyaga", Toast.LENGTH_SHORT).show()
        }
        binding.positiveButton.setOnClickListener {
            dismiss()
        }
    }
}