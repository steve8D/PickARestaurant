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

    // 2/ variable set to true when user wants to navigate to Google Maps
    // otherwise, set to false
    private val _navigateToGoogleMaps = MutableLiveData<Boolean>()
    val navigateToGoogleMaps : LiveData<Boolean>
        get() = _navigateToGoogleMaps

    // 3/ restaurant name for displaying the result as well as adding to the maps query search (Google Maps Intent)
    private val _restaurantName = MutableLiveData<String>()
    val restaurantName: LiveData<String>
        get() = _restaurantName

    // 4/ Place ID of the restaurant to be placed into the google maps search query
    private val _placeID = MutableLiveData<String>()
    val placeID : LiveData<String>
        get() = _placeID

    init {
        Log.i("ResultViewModel", "Result View Model initialised")
        _findAgain.value = false // will be initialised differently once finding a restaurant on places API is implemented
        _navigateToGoogleMaps.value = false

        // temp init values
        _restaurantName.value = "Teadot"
        _placeID.value = "ChIJY-ozCQBzhlQRbw92Fwv95Ak"
    }

    fun findAnotherRestaurant() {
        _findAgain.value = true
    }

    fun navigationToGoogleMapsHandled() {
        _navigateToGoogleMaps.value = true
    }

    fun openGoogleMaps(): Intent {
        val gmmIntentUri =
            Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(_restaurantName.value) + "&query_place_id=" + _placeID.value)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        return mapIntent
    }
}