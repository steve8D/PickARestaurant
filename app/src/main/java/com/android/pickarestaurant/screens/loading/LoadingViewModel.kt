package com.android.pickarestaurant.screens.loading

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingViewModel: ViewModel() {
    // attributes
    // 1/ variable set to true when app has found a restaurant
    // otherwise, set to false
    private val _foundRestaurant = MutableLiveData<Boolean>()
    val foundRestaurant: LiveData<Boolean>
        get() = _foundRestaurant


    init {
        Log.i("LoadingViewModel", "Loading View Model initialised")
        _foundRestaurant.value = true // will be initialised to false once finding a restaurant on places API is implemented
    }

    fun hasFoundRestaurant() {
        _foundRestaurant.value = false
    }
}