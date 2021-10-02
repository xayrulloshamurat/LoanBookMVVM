package com.example.loan_book_mvvm.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.data.ResourceState
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentStory : Fragment(R.layout.fragment_story) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTransaction()
        viewModel.recyclerTransactionsLive.observe(viewLifecycleOwner, {
            when(it.status){
                ResourceState.LOADING->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                ResourceState.SUCCESS->{

                }
                ResourceState.ERROR->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}