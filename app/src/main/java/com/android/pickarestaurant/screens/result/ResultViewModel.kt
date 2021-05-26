package com.android.pickarestaurant.screens.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel: ViewModel() {

    // attributes
    // 1/ variable set to true when user wants to find another restaurant
    // otherwise, set to false
    private val _findAgain = MutableLiveData<Boolean>()
    val findAgain : LiveData<Boolean>
        get() = _findAgain

    // 2/ self-explanatory
    private val _restaurantName = MutableLiveData<String>()
    val restaurantName: LiveData<String>
        get() = _restaurantName

    init {
        _findAgain.value = true // will be initialised differently once finding a restaurant on places API is implemented
    }
}