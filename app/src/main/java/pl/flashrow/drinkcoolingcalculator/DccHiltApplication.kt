package pl.flashrow.drinkcoolingcalculator

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import pl.flashrow.feature.adverts.util.AppLifecycleObserver
import javax.inject.Inject

@HiltAndroidApp
internal class DccHiltApplication : Application() {
    @Inject
    lateinit var appLifecycleObserver: AppLifecycleObserver

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        appLifecycleObserver.register(this)
    }
}