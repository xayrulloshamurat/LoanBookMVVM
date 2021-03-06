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
    var adapter : AdapterTransactionsToRecycler = AdapterTransactionsToRecycler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.recyclerTransactionsLive.observe(viewLifecycleOwner, {
            when(it.status){
                ResourceState.LOADING->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                ResourceState.SUCCESS->{
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    binding.recycler.adapter = adapter
                    adapter.models = it.data !!
                }
                ResourceState.ERROR->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}