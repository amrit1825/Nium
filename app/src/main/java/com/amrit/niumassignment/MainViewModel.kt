package com.amrit.niumassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mCountryListLiveData: MutableLiveData<List<Country>> = MutableLiveData()
    private val mSelectedCountryLiveData: MutableLiveData<Country> = MutableLiveData()
    var mIsNatural: Boolean = true

    fun getCountryList() {
        CountryListRequest().getCountryList(object : ApiResponseListener<List<Country>> {
            override fun onSuccess(data: List<Country>?) {
                mCountryListLiveData.value = data
            }

            override fun onError() {
                TODO("Not yet implemented")
            }
        })
    }

    fun getCountryLiveData() = mCountryListLiveData

    fun setSelectedCountry(country: Country) {
        mSelectedCountryLiveData.value = country
    }

    fun getSelectedCountryLiveData() = mSelectedCountryLiveData

}