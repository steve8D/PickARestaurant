package com.android.pickarestaurant.screens.result

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.pickarestaurant.screens.network.Result

class ResultViewModel(restaurant: Result) : ViewModel() {

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

    private val _restaurant = MutableLiveData<Result>()
    var restaurant : LiveData<Result>
        get() = _restaurant
        set(value) {}

    // 3/ restaurant name for displaying the result as well as adding to the maps query search (Google Maps Intent)
    private val _restaurantName = MutableLiveData<String>()
    val restaurantName: LiveData<String>
        get() = _restaurantName

    // 4/ Place ID of the restaurant to be placed into the google maps search query
    private val _placeID = MutableLiveData<String>()

    init {
        Log.i("ResultViewModel", "Result View Model initialised")
        _findAgain.value = false
        _navigateToGoogleMaps.value = false

        // temp init values
        _restaurant.value = restaurant
        extractRestaurantInfo()
    }

    fun findAnotherRestaurant() {
        _findAgain.value = true
    }

    fun navigationToGoogleMapsHandled() {
        _navigateToGoogleMaps.value = true
    }

    fun extractRestaurantInfo() {
        _restaurantName.value = restaurant.value?.name
        _placeID.value = restaurant.value?.placeID
    }

    fun openGoogleMaps(): Intent {
        val gmmIntentUri =
            Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(_restaurantName.value) + "&query_place_id=" + _placeID.value)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        return mapIntent
    }
}