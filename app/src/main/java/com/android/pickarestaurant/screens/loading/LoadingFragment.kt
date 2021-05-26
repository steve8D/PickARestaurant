package com.android.pickarestaurant.screens.loading

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
import com.android.pickarestaurant.databinding.FragmentLoadingBinding
import com.android.pickarestaurant.databinding.FragmentResultBinding
import com.android.pickarestaurant.screens.result.ResultViewModel

class LoadingFragment : Fragment() {
    private lateinit var viewModel: LoadingViewModel

    private lateinit var binding: FragmentLoadingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate (inflater,
            R.layout.fragment_loading, container, false)

        viewModel = ViewModelProvider(this).get(LoadingViewModel::class.java)

        binding.loadingViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)

//        binding.tempButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loadingFragment_to_resultFragment))
        viewModel.foundRestaurant.observe(this, Observer { hasFoundRestaurant ->
            if (hasFoundRestaurant)
                findNavController().navigate(R.id.action_loadingFragment_to_resultFragment)
        })

        return binding.root
    }
}