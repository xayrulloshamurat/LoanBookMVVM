package com.example.loan_book_mvvm.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.DialogAddBinding
import com.example.loan_book_mvvm.helper.DataHelper
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DebtAndLoanDialog(nContext: Context) : Dialog(nContext) {
    private lateinit var binding: DialogAddBinding
    private val viewModel = MainViewModel(users = DataHelper(db= FirebaseFirestore.getInstance()))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calendar.setOnClickListener {
            val calendar = CalendarDialog(context)
            calendar.show()
        }
        binding.imageCalc.setOnClickListener {

        }
        binding.positiveButton.setOnClickListener {
                var name: String = binding.editContact.text.toString()
                var amount: Double = binding.editMoney.text.toString().toDouble()
                var comments: String = binding.editComments.text.toString()
                var date: Long = binding.calendar.drawingTime
                viewModel.usersFunc(name, amount, comments , date, onSuccesListener = {}, onFailureListener = {})
        }
        binding.negativeButton.setOnClickListener {

        }
        binding.neutralButton.setOnClickListener {
            dismiss()
        }

    }

    fun checkEmpty(): Boolean {
        binding.editContact.text.toString().isEmpty() || binding.editMoney.text.toString()
            .isEmpty() || binding.editComments.text.toString().isEmpty()
        return false
    }
}