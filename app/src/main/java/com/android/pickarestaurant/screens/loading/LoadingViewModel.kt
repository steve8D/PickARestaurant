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
    private val _restaurants : MutableList<Result> = mutableListOf()

    // 4/ The randomly selected restaurant from the list of restaurants
    private val _selectedRestaurant = MutableLiveData<Result>()
    val selectedRestaurant: LiveData<Result>
        get() = _selectedRestaurant

    // 5/ Boolean value indicating whether restaurant has been selected
    private val _hasSelectRestaurant = MutableLiveData<Boolean>()
    val hasSelectRestaurant: LiveData<Boolean>
        get() = _hasSelectRestaurant

    init {
        _hasSelectRestaurant.value = false
    }

    fun launchGoogleSearch(pageToken: String) {
        MapsApi.retrofitService.getRestaurants("${_latitude.value}, ${_longitude.value}", 1500, "restaurant", true, "Insert Your Places API key here", pageToken).enqueue( object: Callback<NearbySearchResponse> {
            override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>) {
                for (res in response.body()?.results!!) {
                    _restaurants.add(res)
                }
                if (response.body()?.nextPageToken == null) {
                    pickARandomRestaurant()
                }
                else {
                    launchGoogleSearch(response.body()?.nextPageToken.toString())
                }
            }
        })
    }

    fun displayRestaurantComplete() {
        _selectedRestaurant.value = null
        _hasSelectRestaurant.value = false
    }

    fun pickARandomRestaurant() {
        val randomInt = Random.nextInt(0,_restaurants.size)
        _selectedRestaurant.value = _restaurants[randomInt]
        _hasSelectRestaurant.value = true
    }
}
