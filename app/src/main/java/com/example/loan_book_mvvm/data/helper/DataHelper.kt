package com.example.loan_book_mvvm.data.helper

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.toObject
import java.util.*
import kotlin.collections.ArrayList

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
        checkContactExists(name, -1 * amount, comments, date, onSuccesListener, onFailureListener)
    }

    fun checkContactExists(
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (String) -> Unit
    ) {
        db.collection("contacts").whereEqualTo("name", name).get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    createUsers(name, amount, comments, date, onSuccesListener, onFailureListener)
                } else {
                    val id = it.documents[0].data!!.getValue("id").toString()
                    val balance = it.documents[0].data!!.getValue("balance").toString().toDouble()
                    updateContact(
                        id,
                        balance,
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

    private fun updateContact(
        id: String,
        balance: Double,
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (String) -> Unit
    ) {
        db.collection("contacts").document(id).update("balance", balance + amount)
            .addOnSuccessListener {
                addTransaction(
                    id,
                    name,
                    amount,
                    comments,
                    date,
                    onSuccesListener,
                    onFailureListener
                )
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
            "balance" to amount,
            "comments" to comments
        )
        db.collection("contacts").document(user.getValue("id").toString())
            .set(user)
            .addOnSuccessListener {
                addTransaction(
                    user["id"].toString(),
                    name,
                    amount,
                    comments,
                    date,
                    onSuccesListener,
                    onFailureListener
                )
            }
            .addOnFailureListener {
                onFailureListener(it.localizedMessage)
            }
    }

    private fun addTransaction(
        id: String,
        name: String,
        amount: Double,
        comments: String,
        date: Long,
        onSuccesListener: () -> Unit,
        onFailureListener: (it: String) -> Unit
    ) {
        val trans = hashMapOf<String, Any>(
            "id" to UUID.randomUUID().toString(),
            "amount" to amount,
            "date" to date
        )
        db.collection("contacts").document(id).collection("transactions")
            .document(trans["id"].toString())
            .set(trans)
            .addOnSuccessListener {
                onSuccesListener.invoke()
            }
            .addOnFailureListener {
                onFailureListener.invoke(it.localizedMessage)
            }
    }

    fun eventChangeListener(onSuccess: (result : ArrayList<Contacts>) -> Unit, onFailure: (msg: String) -> Unit) {
        var result : ArrayList<Contacts> = arrayListOf()
        db.collection("contacts").get()
            .addOnSuccessListener {it->
                it.documents.forEach {
                    result = it.toObject(Contacts::class.java!!)
                    onSuccess.invoke(result)
                }
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}
