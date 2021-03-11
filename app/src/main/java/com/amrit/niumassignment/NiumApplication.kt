package com.amrit.niumassignment

import android.app.Application

class NiumApplication : Application() {

    companion object {
        lateinit var appContext: NiumApplication
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

}