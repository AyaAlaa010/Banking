package com.example.sparksbankingaccount.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.UserTransferItemBinding
import com.example.sparksbankingaccount.models.UserInDetails


class SendMoneyAdapter(
    private val usersList: ArrayList<UserInDetails>,
    var name: String?,
    var senderNo: String?,
    var senderBalance: Int?,
) : RecyclerView.Adapter<SendMoneyAdapter.ViewHolder>() {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        navController = Navigation.findNavController(parent!!)

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_transfer_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = usersList[position]

        holder.itemBinding.tvUserTransferItem.text = data.name
        holder.itemBinding.tvAccountItem.text = data.accountNo
        holder.itemBinding.btnSendmoneyItem.setOnClickListener {
            val bundle = bundleOf("SENDER" to name,
                "RECIEVER" to data.name,
                "RECIEVERNO" to data.accountNo,
                "SENDERNO" to senderNo,
                "SENDER_BALANCE" to senderBalance)
            navController.navigate(
                R.id.action_transferMoneyFragment_to_confirmationFragment, bundle
            )

        }


    }

    override fun getItemCount(): Int {
        return usersList.size
    }


    class ViewHolder(itemBinding: UserTransferItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: UserTransferItemBinding = itemBinding

    }


}