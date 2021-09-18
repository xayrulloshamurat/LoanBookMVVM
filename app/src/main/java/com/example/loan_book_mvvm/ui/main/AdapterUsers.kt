package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R

class AdapterUsers : RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {

    var models: MutableList<AdapterUsers> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(users: AdapterUsers) {

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
