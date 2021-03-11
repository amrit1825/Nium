package com.amrit.niumassignment

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryListRequest {

    fun getCountryList(apiResponseListener: ApiResponseListener<List<Country>>) {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(NiumApplication.appContext.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val countryApiService = retrofit.create(CountryApiService::class.java)

        countryApiService.countryList().enqueue(object : Callback<List<Country>> {

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    apiResponseListener.onSuccess(response.body())
                } else {
                    apiResponseListener.onError()
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                apiResponseListener.onError()
            }
        })

    }
}