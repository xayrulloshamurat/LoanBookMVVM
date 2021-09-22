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
        onFailureListener: (String) -> Unit
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
                onFailureListener.invoke(it.localizedMessage)
            }
    }

    fun addLoan(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (String) -> Unit
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
                onFailureListener.invoke(it.localizedMessage)
            }
    }

    fun createUsers(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: String) -> Unit
    ) {
        val user = hashMapOf<String, Any>(
            "id" to UUID.randomUUID().toString(),
            "name" to name,
            "amount" to amount
        )
        db.collection("contacts")
            .add(user)
            .addOnSuccessListener { documentReference ->
                val trans = hashMapOf<String, Any>(
                    "name" to name,
                    "comments" to comments,
                    "date" to date
                )
                db.collection("contacts").document(user["id"].toString()).collection("transactions")
                    .add(trans)
                    .addOnSuccessListener {
                        onSuccesListener.invoke()
                    }
                    .addOnFailureListener {
                        onFailureListener.invoke(it.localizedMessage)
                    }
            }
            .addOnFailureListener { e ->
                onFailureListener(e.localizedMessage)
            }
    }

    fun changed(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: String) -> Unit
    ) {

    }
}
