package com.android.pickarestaurant.screens.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.pickarestaurant.R
import com.android.pickarestaurant.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var viewModel: ResultViewModel

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result, container, false)

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        binding.resultViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)

        // navigates back to the loading screen if user wants to pick another restaurant
//        binding.pickRestaurantAgainButton.setOnClickListener(Navigation.createNavigateOnClickListener(
//            R.id.action_resultFragment_to_loadingFragment
//        ))
        viewModel.findAgain.observe(this, Observer { findAgain ->
            if (findAgain) {
                findNavController().navigate(R.id.action_resultFragment_to_loadingFragment)
            }
        })
        return binding.root
    }
}