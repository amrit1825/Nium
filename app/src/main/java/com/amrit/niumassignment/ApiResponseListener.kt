package com.amrit.niumassignment

interface ApiResponseListener<T> {
    fun onSuccess(data: T?)
    fun onError()
}