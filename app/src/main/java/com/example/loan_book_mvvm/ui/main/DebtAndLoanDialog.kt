package com.example.loan_book_mvvm.ui.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.loan_book_mvvm.databinding.DialogAddBinding

class DebtAndLoanDialog(
    nContext: Context,
    var onPlus: (name: String, amount: Double, comments: String, date: Long) -> Unit,
    var onMinus: (name: String, amount: Double, comments: String, date: Long) -> Unit
) : Dialog(nContext) {
    private lateinit var binding: DialogAddBinding
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
            var amount = binding.editMoney.text.toString()
            var comments: String = binding.editComments.text.toString()
            var date: Long = binding.calendar.drawingTime
            if (name.isNotEmpty() && amount.isNotEmpty() && comments.isNotEmpty()) {
                onPlus.invoke(name, amount.toDouble(), comments, date)
            } else {
                Toast.makeText(context, "Qatorlarni to'ldiring!", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
        binding.negativeButton.setOnClickListener {
            var name: String = binding.editContact.text.toString()
            var amount = binding.editMoney.text.toString()
            var comments: String = binding.editComments.text.toString()
            var date: Long = binding.calendar.drawingTime
            if (name.isNotEmpty() && amount.isNotEmpty() && comments.isNotEmpty()) {
                onMinus.invoke(name, amount.toDouble(), comments, date)
            } else {
                Toast.makeText(context, "Qatorlarni to'ldiring!", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
        binding.neutralButton.setOnClickListener {
            dismiss()
        }

    }
}