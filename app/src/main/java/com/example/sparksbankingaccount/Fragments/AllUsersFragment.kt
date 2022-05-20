package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbankingaccount.Adapters.AllUserAdapter
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.FragmentAllUsersBinding
import com.example.sparksbankingaccount.generalclasses.SelectAll


class AllUsersFragment : Fragment() {

    lateinit var binding:FragmentAllUsersBinding
    lateinit var adapter:AllUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_users, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= AllUserAdapter(SelectAll(requireContext()).selectAllUsers())
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recUsers.adapter = adapter
        binding.recUsers.layoutManager = layoutManager
        backAction()

    }

fun backAction(){
    binding.alluserBack.setOnClickListener {
        findNavController().popBackStack()
    }



}
}