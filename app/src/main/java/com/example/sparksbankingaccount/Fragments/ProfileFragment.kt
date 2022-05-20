package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import android.util.Log
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
import com.example.sparksbankingaccount.database.DBHelper
import com.example.sparksbankingaccount.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        navController = Navigation.findNavController(container!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getInt("ID")!!.toInt()
        Log.i(" id", "onViewCreated: " + id)

        backAction()
        setProfileValues(id)
    }

    fun backAction() {
        binding.profileBack.setOnClickListener {

            findNavController().popBackStack()

        }


    }


    fun setProfileValues(id: Int) {

        val db = DBHelper(requireContext(), null)
        var arr = db.getSpecificUser(id)
        binding.availableBalanceValue.text = arr[0].balance.toString()
        binding.userEmailValue.text = arr[0].email
        binding.userNameValue.text = arr[0].name
        binding.userAccountnoValue.text = arr[0].accountNo
        val bundle = bundleOf("NAME" to arr[0].name,
            "SENDERNO" to arr[0].accountNo,
            "SENDERBALANCE" to arr[0].balance)
        binding.btnTransferMoney.setOnClickListener {
            navController.navigate(
                R.id.action_profileFragment_to_transferMoneyFragment, bundle
            )
        }


    }

}