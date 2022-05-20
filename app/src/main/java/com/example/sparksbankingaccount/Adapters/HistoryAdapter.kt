package com.example.sparksbankingaccount.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.HistoryItemBinding
import com.example.sparksbankingaccount.models.HistoryBojo

class HistoryAdapter(private val usersList: ArrayList<HistoryBojo>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.history_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = usersList[position]
        holder.itemBinding.tvSender.text = data.sender
        holder.itemBinding.tvSendAccountnum.text = "Acc No: " + data.senderNo
        holder.itemBinding.tvReciever.text = data.reciever
        holder.itemBinding.tvRecieverAccountnum.text = "Acc No: " + data.recieverNo
        holder.itemBinding.tvAmount.text = "amount : " + data.amount.toString()
    }

    override fun getItemCount(): Int {
        return usersList.size
    }


    class ViewHolder(itemBinding: HistoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: HistoryItemBinding = itemBinding

    }


}