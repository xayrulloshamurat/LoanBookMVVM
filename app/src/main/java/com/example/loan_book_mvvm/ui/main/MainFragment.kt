package com.example.loan_book_mvvm.ui.main


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.data.ResourceState
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import com.example.loan_book_mvvm.data.helper.Contacts
import com.google.firebase.firestore.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    var adapter : AdapterСontacts = AdapterСontacts()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        recyclerView = binding.recycler
        binding.fab.setOnClickListener {
            launchCustomAlertDialog()
        }
        viewModel.contactsLive.observe(viewLifecycleOwner, {
            when(it.status){
                ResourceState.LOADING->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                ResourceState.SUCCESS ->{
                    recyclerView.adapter = adapter
                    adapter.models = it.data!!
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


        viewModel.transactionsLive.observe(viewLifecycleOwner, {
            when (it) {
                "loading" -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                "success" -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    viewModel.contactsFun()
                }
                it -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun launchCustomAlertDialog() {
        val myDialog = DebtAndLoanDialog(
            requireContext(),
            onPlus = { name, amount, comments, date ->
                viewModel.debtFunc(name, amount, comments, date)
            },
            onMinus = { name, amount, comments, date ->
                viewModel.loanFunc(name, amount, comments, date)
            },
        )
        myDialog.show()
    }
}
