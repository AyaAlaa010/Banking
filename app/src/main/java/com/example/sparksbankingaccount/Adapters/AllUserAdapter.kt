package com.example.sparksbankingaccount.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.AllusersItemBinding
import com.example.sparksbankingaccount.models.HistoryBojo
import com.example.sparksbankingaccount.models.UserInDetails


class AllUserAdapter(private val usersList: ArrayList<UserInDetails>) :
    RecyclerView.Adapter<AllUserAdapter.ViewHolder>() {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        navController = Navigation.findNavController(parent!!)

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.allusers_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = usersList[position]
        holder.itemBinding.userName.text = data.name
        holder.itemBinding.userAccountNum.text = data.accountNo
        holder.itemBinding.userBalanceValue.text = data.balance.toString()
        holder.itemView.setOnClickListener {

            navController.navigate(
                R.id.action_allUsersFragment_to_profileFragment
            )

        }


        holder.itemView.setOnClickListener {
            val bundle = bundleOf("ID" to data.id)
            navController.navigate(R.id.action_allUsersFragment_to_profileFragment, bundle)


        }


    }

    override fun getItemCount(): Int {
        return usersList.size
    }


    class ViewHolder(itemBinding: AllusersItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: AllusersItemBinding = itemBinding

    }


}