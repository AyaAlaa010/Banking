package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.Adapters.SendMoneyAdapter
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.database.DBHelper
import com.example.sparksbankingaccount.databinding.FragmentTransferMoneyBinding
import com.example.sparksbankingaccount.generalclasses.SelectAll

class TransferMoneyFragment : Fragment() {

    lateinit var binding: FragmentTransferMoneyBinding
    lateinit var adapter: SendMoneyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transfer_money, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name = arguments?.getString("NAME")
        var senderNo = arguments?.getString("SENDERNO")
        var senderBalance = arguments?.getInt("SENDERBALANCE")


        binding.tvUserName.text = name


        // adapter= SendMoneyAdapter(SelectAll(requireContext()).selectAllUsers(),name,senderNo,senderBalance)
//selectNot
        val db = DBHelper(requireContext(), null)

        adapter = SendMoneyAdapter(db.selectNot(name!!), name, senderNo, senderBalance)

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recUsersTransfer.adapter = adapter
        binding.recUsersTransfer.layoutManager = layoutManager

    }


}