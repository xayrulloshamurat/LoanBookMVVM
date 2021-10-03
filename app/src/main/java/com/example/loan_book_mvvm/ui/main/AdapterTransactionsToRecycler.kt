package com.example.loan_book_mvvm.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.data.helper.Contacts
import com.example.loan_book_mvvm.data.helper.TransactionData
import com.example.loan_book_mvvm.databinding.ItemTransactionsBinding
import com.example.loan_book_mvvm.databinding.ItemUsersBinding

class AdapterTransactionsToRecycler() :
    RecyclerView.Adapter<AdapterTransactionsToRecycler.AdapterTransactionsViewHolder>() {

    var models: List<TransactionData> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class AdapterTransactionsViewHolder(var binding: ItemTransactionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(transactionData: TransactionData) {
            binding.apply {
                name.text = transactionData.name
                amount.text = transactionData.amount.toString()
                date.text = transactionData.date.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTransactionsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_transactions, parent, false)
        var binding = ItemTransactionsBinding.bind(view)
        return AdapterTransactionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterTransactionsViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}