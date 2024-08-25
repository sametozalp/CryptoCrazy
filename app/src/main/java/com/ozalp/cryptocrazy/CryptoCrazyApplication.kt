package com.ozalp.cryptocrazy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoCrazyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}