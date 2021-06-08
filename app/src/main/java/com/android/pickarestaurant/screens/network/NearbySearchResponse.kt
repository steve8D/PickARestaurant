package com.android.pickarestaurant.screens.network

import com.squareup.moshi.Json

data class NearbySearchResponse (
    @Json(name = "html_attributions")
    val htmlAttributions: List<String>,
    @Json(name = "next_page_token")
    val nextPageToken: String,
    val results: List<Result>,
    val status: String
)

data class Result (
    @Json(name = "business_status")
    val businessStatus: String?,
    val geometry: Geometry?,
    @Json(name = "icon")
    val iconImgUrl: String?,
    val name: String?,
    val opening_hours: OpeningHours?,
    val photos: List<Photo>?,
    @Json(name = "place_id")
    val placeID: String?,
    @Json(name = "plus_code")
    val plusCode: PlusCode?,
    @Json(name = "price_level")
    val priceLevel: Int?,
    val rating: Double?,
    val reference: String?,
    val scope: String?,
    val types: List<String>?,
    @Json(name = "user_ratings_total")
    val userRatingsCount: Int?,
    val vicinity: String?
)

data class Geometry (
    val location: Location,
    val viewport: Viewport,
)

data class Location (
    @Json(name = "lat")
    val latitude: Double,
    @Json(name = "lng")
    val longitude: Double
)

data class Viewport (
    @Json(name = "northeast")
    val northEast: Location,
    @Json(name = "southwest")
    val southWest: Location
)

data class OpeningHours (
    val open_now: Boolean
)

data class Photo (
    val height: Int,
    val width: Int,
    @Json(name = "html_attributions")
    val htmlAttributions: List<String>,
    @Json(name = "photo_reference")
    val photoRef: String
)

data class PlusCode (
    @Json(name = "compound_code")
    val compoundCode: String,
    @Json(name = "global_code")
    val globalCode: String
)