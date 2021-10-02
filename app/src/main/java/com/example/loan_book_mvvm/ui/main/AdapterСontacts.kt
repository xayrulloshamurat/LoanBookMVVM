package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.data.helper.Contacts
import com.example.loan_book_mvvm.data.helper.TransactionData
import com.example.loan_book_mvvm.databinding.ItemUsersBinding

class AdapterContacts(var fragment: MainFragment) : RecyclerView.Adapter<AdapterContacts.UsersViewHolder>() {

    var models: List<Contacts> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var transactionModels: List<Contacts> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UsersViewHolder(var binding: ItemUsersBinding, var fragment: MainFragment) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(contacts: Contacts, transactionData: TransactionData) {
            binding.username.text = contacts.name
            binding.money.text = contacts.balance.toString()
            binding.more.setOnClickListener {
                fragment.onOptionsButtonClicked(binding.more)
            }
            binding.money.text = transactionData.amount.toString()
            binding.username.text = transactionData.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent , false)
        var binding = ItemUsersBinding.bind(view)
        return UsersViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int, transPosition : Int) {
       holder.populateModel(models[position])
    }
    override fun getItemCount() = models.size
}
