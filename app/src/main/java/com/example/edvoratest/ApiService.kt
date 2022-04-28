package com.example.edvoratest

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("/rides")
    fun getPosts(): Call<MutableList<PostModel>>
}