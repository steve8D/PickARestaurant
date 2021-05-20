package com.android.pickarestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.android.pickarestaurant.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.pickRestaurantAgainButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_resultFragment_to_loadingFragment))
        return binding.root
    }
}