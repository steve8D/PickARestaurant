package com.android.pickarestaurant.screens.result

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
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
        Log.i("ResultViewModel", "Result View Model initialised")
        _findAgain.value = false // will be initialised differently once finding a restaurant on places API is implemented
    }

    fun findAnotherRestaurant() {
        _findAgain.value = true
    }
}