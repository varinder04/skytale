package com.varinder.scytale.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SkyTaleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}