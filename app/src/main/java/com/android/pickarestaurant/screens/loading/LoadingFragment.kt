package com.android.pickarestaurant.screens.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.android.pickarestaurant.R
import com.android.pickarestaurant.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoadingBinding = DataBindingUtil.inflate (inflater,
            R.layout.fragment_loading, container, false)
        binding.tempButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loadingFragment_to_resultFragment))
        return binding.root
    }
}