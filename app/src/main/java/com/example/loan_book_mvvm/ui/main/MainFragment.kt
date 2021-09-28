package com.example.loan_book_mvvm.ui.main


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import com.example.loan_book_mvvm.helper.User
import com.google.firebase.firestore.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: AdapterUsers
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        recyclerView = binding.recycler
        userArrayList = arrayListOf()
        myAdapter = AdapterUsers(userArrayList)
        recyclerView.adapter = myAdapter
        eventChangeListener()
        binding.fab.setOnClickListener {
            launchCustomAlertDialog()
        }

        viewModel.usersLive.observe(viewLifecycleOwner, {
            when (it) {
                "loading" -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                "success" -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
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

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("contacts").addSnapshotListener((object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {
                    Log.e("Firestore error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.document.toObject(User::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        }))

    }
}