package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.FragmentConfirmationBinding


class ConfirmationFragment : Fragment() {
    private lateinit var navController: NavController

    lateinit var binding: FragmentConfirmationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(container!!)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var balance = requireArguments().getInt("SENDER_BALANCE")
        var sender = requireArguments().getString("SENDER")
        var reciever = requireArguments().getString("RECIEVER")
        var senderNum = requireArguments().getString("SENDERNO")
        var recieverNum = requireArguments().getString("RECIEVERNO")
        binding.btSendmoney.setOnClickListener {
            var transferAmount = binding.etAmountnum.text.toString()


            if (binding.etAmountnum.text!!.isNotEmpty()) {
                if (balance > transferAmount.toInt()) {

                    val bundle = bundleOf("BALANCE" to balance,
                        "Amount" to transferAmount,
                        "SENDER" to sender,
                        "SENDER_NUM" to senderNum,
                        "RECIEVER" to reciever,
                        "RECIEVERNUM" to recieverNum)

                    navController.navigate(
                        R.id.action_confirmationFragment_to_customDialogFragment, bundle
                    )
                    binding.etAmountnum.text?.clear()


                } else {

                    binding.etAmountnum.setError("please enter a valid amount")

                }

            } else {

                binding.etAmountnum.setError("please enter a valid amount")

            }
            //  binding.etAmountnum.text?.clear()

        }
        binding.tvSender.text = sender
        binding.tvReciever.text = reciever
        binding.tvRecieverAccountnum.text = "Acc no : " + recieverNum
        binding.tvSendAccountnum.text = "Acc no : " + senderNum
        binding.tvTotalBalance.text = "Available Balance : " + balance

    }


}