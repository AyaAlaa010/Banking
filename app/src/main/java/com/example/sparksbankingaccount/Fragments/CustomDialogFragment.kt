package com.example.sparksbankingaccount.Fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.R.layout.fragment_custom_dialog
import com.example.sparksbankingaccount.database.DBHelper
import com.example.sparksbankingaccount.database.HistoryDBHelper
import com.example.sparksbankingaccount.databinding.FragmentCustomDialogBinding
import com.example.sparksbankingaccount.models.UserInDetails
import java.util.*

class CustomDialogFragment : DialogFragment() {

    lateinit var binding: FragmentCustomDialogBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DataBindingUtil.inflate(inflater, fragment_custom_dialog, container, false)
//        navController= Navigation.findNavController(container!!)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //"BALANCE" to balance,"Amount" to transferAmount,"SENDER" to sender,"SENDER_NUM" to senderNum,
        //            "RECIEVER" to reciever ,"RECIEVERNUM" to recieverNum
        var sender = requireArguments().getString("SENDER")
        var senderNum = requireArguments().getString("SENDER_NUM")
        var reciever = requireArguments().getString("RECIEVER")
        var recieverNum = requireArguments().getString("RECIEVERNUM")
        var amountTransfer = requireArguments().getString("Amount")!!.toInt()
        var totalSenderBalance = requireArguments().getInt("BALANCE")!!

        val db = DBHelper(requireContext(), null)
        var arr = db.getSpecificUserByName(reciever!!)
        var totalRecieverBalance = arr[0].balance

        var senderBalance = totalSenderBalance!!.toInt() - amountTransfer!!.toInt()
        var recieverBalance = totalRecieverBalance + amountTransfer!!.toInt()

//        binding.availableBalanceValue.text=arr[0].balance.toString()

        binding.tvAmountTransfer.text = amountTransfer.toString()
        binding.tvRecieverSuc.text = "To " + reciever + "  Acc No :" + recieverNum
        binding.tvSenderSuc.text = "From " + sender + "  Acc No : " + senderNum
        binding.transactionId.text = "Transaction id :\n" + UUID.randomUUID().toString()
        binding.tvAmountTransfer.text = amountTransfer.toString()
        insertHistory(sender!!, senderNum!!, reciever, recieverNum!!, amountTransfer)

        binding.btDone.setOnClickListener {
            var fragMan: FragmentManager? = fragmentManager
            fragMan!!.beginTransaction().replace(R.id.fragmentContainerView, AllUsersFragment())
                .commit()
            dismiss()

        }

        updateUser(senderBalance, sender!!)
        updateUser(recieverBalance, reciever!!)

    }

    fun updateUser(balance: Int, name: String) {

        val db = DBHelper(requireContext(), null)
        var isUpdated = db.updateEmployee(balance, name)
        if (isUpdated != -1) {
            Toast.makeText(requireContext(), "updated", Toast.LENGTH_LONG).show()
        } else {

            Toast.makeText(requireContext(), "not updated", Toast.LENGTH_LONG).show()
        }
    }

    fun insertHistory(
        sender: String,
        senderNo: String,
        reciever: String,
        recieverNo: String,
        amount: Int,
    ) {
        val db = HistoryDBHelper(requireContext(), null)
        db.addUser(sender, senderNo, reciever, recieverNo, amount)
    }
}