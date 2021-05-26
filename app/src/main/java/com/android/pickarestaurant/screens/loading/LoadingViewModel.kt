package com.android.pickarestaurant.screens.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingViewModel: ViewModel() {
    private val _foundRestaurant = MutableLiveData<Boolean>()
    val foundRestaurant: LiveData<Boolean>
        get() = _foundRestaurant
    init {

    }
}