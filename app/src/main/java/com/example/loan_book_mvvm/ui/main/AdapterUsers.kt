package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.ItemUsersBinding
import com.example.loan_book_mvvm.helper.User
import com.google.firebase.firestore.FirebaseFirestore

class AdapterUsers( var userList : ArrayList<User>) : RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var name: TextView = itemView.findViewById(R.id.username)
            var comments: TextView = itemView.findViewById(R.id.comments)
            var money: TextView = itemView.findViewById(R.id.money)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
      val user : User = userList[position]
        holder.name.text = user.name
        holder.comments.text = user.comments
        holder.money.text = user.amount.toString()
    }
    override fun getItemCount() = userList.size
}
