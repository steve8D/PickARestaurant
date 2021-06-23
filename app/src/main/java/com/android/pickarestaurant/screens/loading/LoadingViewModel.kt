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
import kotlin.random.Random

class LoadingViewModel: ViewModel() {
    // attributes
    // 1/ latitude
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String>
        get() = _latitude

    // 2/ longitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String>
        get() = _longitude

    // 3/ List of Restaurants returned from Googles API
    private val _restaurants = MutableLiveData<List<Result>>()

    // 4/ The randomly selected restaurant from the list of restaurants
    private val _selectedRestaurant = MutableLiveData<Result>()
    val selectedRestaurant: LiveData<Result>
        get() = _selectedRestaurant

    init {

    }

    fun LaunchGoogleSearch() {
        MapsApi.retrofitService.getRestaurants("${_latitude.value}, ${_longitude.value}", 1500, "restaurant", "", "AIzaSyC7Rro_WLInpUuCrLl9r-uHogpmIsCAAyo").enqueue( object: Callback<NearbySearchResponse> {
            override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>) {
                _restaurants.value = response.body()?.results
                pickARandomRestaurant()
            }
        })
    }

    fun displayRestaurantComplete() {
        _selectedRestaurant.value = null
    }

    fun pickARandomRestaurant() {
        val randomInt = Random.nextInt(0,_restaurants.value!!.size)
        _selectedRestaurant.value = _restaurants.value!![randomInt]
    }
}