package com.example.loan_book_mvvm.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.ActivityMainBinding
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import com.example.loan_book_mvvm.databinding.FragmentSignInBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var dataPickerDialog: DatePickerDialog
    private lateinit var customAlertDialogView: View
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        binding = FragmentMainBinding.bind(view)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        dataPickerDialog = DatePickerDialog(requireContext())
        binding.fab.setOnClickListener(View.OnClickListener {
            customAlertDialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add, null, false)
            launchCustomAlertDialog()
        })
    }
    fun launchCustomAlertDialog() {

        materialAlertDialogBuilder.setView(customAlertDialogView)
            .setTitle("Добавление")
            .setPositiveButton("Минус") { dialog, _ ->

                dialog.dismiss()
            }
            .setNegativeButton("ПЛЮС"){dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Отмена"){dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}