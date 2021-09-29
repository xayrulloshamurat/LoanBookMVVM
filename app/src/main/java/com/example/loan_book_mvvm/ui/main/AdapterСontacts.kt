package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.data.helper.Contacts
import com.example.loan_book_mvvm.databinding.ItemUsersBinding

class AdapterСontacts : RecyclerView.Adapter<AdapterСontacts.UsersViewHolder>() {
    
    var models: List<Contacts> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UsersViewHolder(var binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(contacts: Contacts) {
            binding.username.text = contacts.name
            binding.money.text = contacts.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        var binding  = ItemUsersBinding.inflate(LayoutInflater.from(parent.context))
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}
