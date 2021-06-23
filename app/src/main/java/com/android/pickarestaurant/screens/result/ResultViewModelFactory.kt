package com.android.pickarestaurant.screens.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.pickarestaurant.screens.network.Result

class ResultViewModelFactory(private val restaurant: Result): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(restaurant) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}