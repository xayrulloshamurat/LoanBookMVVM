package com.example.loan_book_mvvm.ui.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        binding = FragmentMainBinding.bind(view)
        binding.fab.setOnClickListener {
            launchCustomAlertDialog()
        }
        binding.button.setOnClickListener {
            if(binding.edit.text != null){
                val user = hashMapOf(
                    "first" to  binding.edit.text.toString() ,
                    "last" to "Lovelace",
                    "born" to 1815
                )

// Add a new document with a generated ID
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }
            }
            }
        }


        // Create a new user with a first and last name



    fun launchCustomAlertDialog() {
        val myDialog = DebtAndLoanDialog(requireContext())
        myDialog.show()
    }
}