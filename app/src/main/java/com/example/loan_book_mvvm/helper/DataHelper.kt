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
        checkContactExists(name, amount, comments, date, onSuccesListener, onFailureListener)
    }

    fun addLoan(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (String) -> Unit
    ) {
        checkContactExists(name, -1*amount, comments, date, onSuccesListener, onFailureListener)
    }

    fun checkContactExists(  name: String, amount: Double, comments: String, date: Long, onSuccesListener: () -> Unit, onFailureListener: (String) -> Unit) {
        db.collection("users").whereEqualTo("name", name).get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    createUsers(name, amount, comments, date, onSuccesListener, onFailureListener)
                } else {
                    val id = it.documents[0].data!!.getValue("id").toString()
                    val balance = it.documents[0].data!!.getValue("amount").toString().toDouble()
                    updateContact( id, balance, name, amount, comments, date, onSuccesListener, onFailureListener)
                }
            }
            .addOnFailureListener {
                onFailureListener.invoke(it.localizedMessage)
            }
    }

    private fun updateContact(id: String, balance : Double,name: String, amount: Double, comments: String, date: Long, onSuccesListener: () -> Unit, onFailureListener: (String) -> Unit) {
        db.collection("contacts").document(id).update("amount", balance+amount)
            .addOnSuccessListener {
                addTransaction(id, name, amount, comments, date, onSuccesListener, onFailureListener)
            }
            .addOnFailureListener {
                onFailureListener.invoke(it.message.toString())
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
        db.collection("contacts").document(user.getValue("id").toString())
            .set(user)
            .addOnSuccessListener { documentReference ->
              addTransaction(user["id"].toString(), name,amount,comments,date, onSuccesListener, onFailureListener)
            }
            .addOnFailureListener { e ->
                onFailureListener(e.localizedMessage)
            }
    }

    private fun addTransaction(id: String, name: String, amount: Double, comments: String, date: Long, onSuccesListener: () -> Unit, onFailureListener: (it: String) -> Unit) {
        val trans = hashMapOf<String, Any>(
            "name" to name,
            "comments" to comments,
            "date" to date
        )
        db.collection("contacts").document(id).collection("transactions")
            .document()
            .set(trans)
            .addOnSuccessListener {
                onSuccesListener.invoke()
            }
            .addOnFailureListener {
                onFailureListener.invoke(it.localizedMessage)
            }
    }
}
