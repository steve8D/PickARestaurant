package com.android.pickarestaurant.screens.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://maps.googleapis.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GoogleMapsApi {
    @GET("/maps/api/place/nearbysearch/json")
    fun getRestaurants(@Query("location") location: String, @Query("radius") radius: Int, @Query("type") type: String, @Query("opennow") isOpen: Boolean, @Query("key") key: String, @Query("pagetoken") pageToken: String): Call<NearbySearchResponse>
}
object MapsApi {
    val retrofitService : GoogleMapsApi by lazy { retrofit.create(GoogleMapsApi::class.java) }
}