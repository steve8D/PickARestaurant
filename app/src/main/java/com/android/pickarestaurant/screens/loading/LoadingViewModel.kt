package com.android.pickarestaurant.screens.loading

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.pickarestaurant.screens.network.MapsApi
import com.android.pickarestaurant.screens.network.NearbySearchResponse
import com.android.pickarestaurant.screens.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingViewModel: ViewModel() {
    // attributes
    // 1/ variable set to true when app has found a restaurant
    // otherwise, set to false
    private val _foundRestaurant = MutableLiveData<Boolean>()
    val foundRestaurant: LiveData<Boolean>
        get() = _foundRestaurant

    // 2/ latitude
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String>
        get() = _latitude

    // 3/ longitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String>
        get() = _longitude

    // 4/ List of Restaurants returned from Googles API
    private val _restaurants = MutableLiveData<List<Result>>()
    val restaurants: LiveData<List<Result>>
        get() = _restaurants

    // 5/ The randomly selected restaurant from the list of  restaurants
    private val _navigateToSelectedRestaurant = MutableLiveData<Result>()
    val navigateToSelectedProperty: LiveData<Result>
        get() = _navigateToSelectedRestaurant

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        Log.i("LoadingViewModel", "Loading View Model initialised")
        _foundRestaurant.value = true // will be initialised to false once finding a restaurant on places API is implemented
    }

    fun LaunchGoogleSearch() {
        MapsApi.retrofitService.getRestaurants("${_latitude.value}, ${_longitude.value}", 1500, "restaurant", "", "AIzaSyC7Rro_WLInpUuCrLl9r-uHogpmIsCAAyo").enqueue( object: Callback<NearbySearchResponse> {
            override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>) {
                _response.value = response.body()?.results?.size.toString()

            }
        })
        Log.i("LoadingViewModel" ,_restaurants.value.toString())
    }

    fun hasFoundRestaurant() {
        _foundRestaurant.value = false
    }
}