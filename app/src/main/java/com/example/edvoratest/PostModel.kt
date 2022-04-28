package com.example.edvoratest

data class PostModel (
    val id : Int? =null,
    val origin_station_code: String? =null,
    val station_path: Array<Int>? =null,
val destination_station_code: Int? = null,
val date: String? =null,
    val map_url: String?=null,
    val state: String? = null,
val city: String? = null

        )
