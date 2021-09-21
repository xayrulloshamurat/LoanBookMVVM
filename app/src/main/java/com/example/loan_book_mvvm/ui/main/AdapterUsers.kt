package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.ItemUsersBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterUsers : RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {
    private lateinit var binding: ItemUsersBinding
    var models: MutableList<FirebaseFirestore> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(db: FirebaseFirestore) {
            binding = ItemUsersBinding.bind(itemView)
            db.collection("contacts").get()
                .addOnSuccessListener {
                    binding.username.text = "name"
                    binding.comments.text = "comments"
                    binding.money.text = "amount"
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

}
