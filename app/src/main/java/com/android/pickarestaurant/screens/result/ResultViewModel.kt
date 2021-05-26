package com.android.pickarestaurant.screens.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel: ViewModel() {

    // attributes
    private val _restaurantName = MutableLiveData<String>()
    val restaurantName: LiveData<String>
        get() = _restaurantName

    init {

    }
}