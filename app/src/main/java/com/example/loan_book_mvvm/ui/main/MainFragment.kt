package com.example.loan_book_mvvm.ui.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding
    private val viewModel : MainViewModel by viewModel()
    var adapter = AdapterUsers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.fab.setOnClickListener {
            launchCustomAlertDialog()
            binding.recycler.adapter = adapter
            setData()
        }

        viewModel.usersLive.observe(viewLifecycleOwner, {
            when(it){
                "loading"->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                "success"->{
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
                it->{
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


// Create a new user with a first and last name


    fun launchCustomAlertDialog() {
        val myDialog = DebtAndLoanDialog(requireContext(), viewModel)
        myDialog.show()
    }
    fun setData(){

    }
}