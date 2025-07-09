package pl.flashrow.drinkcoolingcalculator

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class DccHiltApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}