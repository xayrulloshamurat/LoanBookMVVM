package com.example.loan_book_mvvm.helper

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class DataHelper(private val db: FirebaseFirestore) {

    fun addDebt(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: Exception) -> Unit
    ) {
        val docRef = db.collection("users").whereEqualTo("name", name)
        docRef.get()
            .addOnSuccessListener {
                    createUsers(
                        name,
                        amount,
                        comments,
                        date,
                        onSuccesListener,
                        onFailureListener
                    )
                }
            .addOnFailureListener {
                onFailureListener.invoke(it)
            }
    }

    fun addLoan(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: Exception) -> Unit
    ) {
        val docRef = db.collection("users").whereEqualTo("name", name)
        docRef.get()
            .addOnSuccessListener {
                if (docRef != null) {
                    changed(
                        name,
                        amount,
                        comments,
                        date,
                        onSuccesListener,
                        onFailureListener
                    )
                } else {
                    createUsers(
                        name,
                        amount,
                        comments,
                        date,
                        onSuccesListener,
                        onFailureListener
                    )
                }
            }
            .addOnFailureListener {
                onFailureListener.invoke(it)
            }
    }

    fun createUsers(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: Exception) -> Unit
    ) {
        val user = hashMapOf<String, Any>(
            "id" to UUID.randomUUID().toString(),
            "name" to name,
            "amount" to amount,
            "comments" to comments,
            "date" to date,
        )
        db.collection("contacts")
            .add(user)
            .addOnSuccessListener { documentReference ->
                onSuccesListener()
            }
            .addOnFailureListener { e ->
                onFailureListener(e)
            }
    }

    fun changed(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: Exception) -> Unit
    ) {

    }
}