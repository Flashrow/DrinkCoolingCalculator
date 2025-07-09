package pl.flashrow.feature.adverts.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLifecycleObserver @Inject constructor(
    private val appOpenAdManager: AppOpenAdManagerInterface
) : LifecycleEventObserver, Application.ActivityLifecycleCallbacks {

    private var currentActivity: Activity? = null
    private var isActivityChangingConfigurations = false

    companion object {
        @Volatile
        var isShowingAd = false

        @Volatile
        var appOpenAdsDisabled = false

        @Volatile
        var appOpenAdShownThisSession = false
    }

    fun register(application: Application) {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_START) {

            if (AppOpenAdManager.adJustDismissed) {
                AppOpenAdManager.adJustDismissed = false
                if (!appOpenAdManager.isAdAvailable) appOpenAdManager.loadAd()
                return
            }

            if (appOpenAdsDisabled) {
                return
            }

            if (isShowingAd) {
                return
            }

            if (appOpenAdShownThisSession) {
                if (!appOpenAdManager.isAdAvailable) appOpenAdManager.loadAd()
                return
            }

            if (isActivityChangingConfigurations) {
                if (!appOpenAdManager.isAdAvailable) appOpenAdManager.loadAd()
                return
            }

            currentActivity?.let { activity ->
                appOpenAdManager.showAdIfAvailable(activity)
            } ?: run {
                if (!appOpenAdManager.isAdAvailable) appOpenAdManager.loadAd()
            }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        this.currentActivity = activity
        isActivityChangingConfigurations = false
    }

    override fun onActivityResumed(activity: Activity) {
        this.currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
    }
}
