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

    init {
        _foundRestaurant.value = false
    }

    fun LaunchGoogleSearch() {
        MapsApi.retrofitService.getRestaurants("${_latitude.value}, ${_longitude.value}", 1500, "restaurant", "", "AIzaSyC7Rro_WLInpUuCrLl9r-uHogpmIsCAAyo").enqueue( object: Callback<NearbySearchResponse> {
            override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable) {
                Log.i("LoadingViewModel", t.message.toString())
            }

            override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>) {
                _restaurants.value = response.body()?.results
                Log.i("LoadingViewModel", response.body()?.results?.size.toString())
                pickARandomRestaurant()
            }
        })
    }

    fun hasFoundRestaurant() {
        _foundRestaurant.value = true
    }

    fun pickARandomRestaurant() {
        val randomInt = Random.nextInt(0,_restaurants.value!!.size+1)
        Log.i("LoadingViewModel", randomInt.toString())
        hasFoundRestaurant()
    }
}