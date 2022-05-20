package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.Adapters.AllUserAdapter
import com.example.sparksbankingaccount.Adapters.HistoryAdapter
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.FragmentTransactionHistoryBinding
import com.example.sparksbankingaccount.generalclasses.SelectAll

class TransactionHistoryFragment : Fragment() {

    lateinit var binding: FragmentTransactionHistoryBinding
    lateinit var adapter: HistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_transaction_history,
            container,
            false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryAdapter(SelectAll(requireContext()).selectAllHistory())
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recHistory.adapter = adapter
        binding.recHistory.layoutManager = layoutManager

        binding.historyBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}