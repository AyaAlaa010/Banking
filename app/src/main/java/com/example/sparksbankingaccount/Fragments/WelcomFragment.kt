package com.example.sparksbankingaccount.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sparksbankingaccount.R
import com.example.sparksbankingaccount.databinding.FragmentWelcomBinding


class WelcomFragment : Fragment() {
lateinit var binding:FragmentWelcomBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcom, container, false)
        navController = Navigation.findNavController(container!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShow.setOnClickListener {
            navController.navigate(
                R.id.action_welcomFragment_to_allUsersFragment2
            )
        }
            binding.btnHistory.setOnClickListener{
                navController.navigate(
                    R.id.action_welcomFragment_to_transactionHistoryFragment
                )


        }
    }


}