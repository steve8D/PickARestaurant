package com.android.pickarestaurant.screens.result

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.pickarestaurant.R
import com.android.pickarestaurant.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var viewModel: ResultViewModel

    private lateinit var binding: FragmentResultBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater)

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        val restaurant = ResultFragmentArgs.fromBundle(requireArguments()).restaurant
        val viewModelFactory = ResultViewModelFactory(restaurant)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModel = viewModel

        // navigates back to the loading screen if user wants to pick another restaurant
        viewModel.findAgain.observe(this, Observer { findAgain ->
            if (findAgain) {
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToLoadingFragment())
            }
        })

        // navigates user to Google Map for the restaurant
        viewModel.navigateToGoogleMaps.observe(this, Observer { navigateToGoogleMaps ->
            if (navigateToGoogleMaps) {
                startActivity(viewModel.openGoogleMaps())
            }
        })
        return binding.root
    }
}