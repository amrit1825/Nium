package com.amrit.niumassignment

import retrofit2.Call
import retrofit2.http.GET

interface CountryApiService {

    @GET("all")
    fun countryList(): Call<List<Country>>

}