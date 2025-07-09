package pl.flashrow.feature.adverts.util // Adjust to your package

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.flashrow.dcc.feature.adverts.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

private const val LOG_TAG = "AppOpenAdManager"
private const val TEST_AD_UNIT_ID = BuildConfig.APP_OPEN_AD_ID

interface AppOpenAdManagerInterface {
    fun loadAd()
    fun showAdIfAvailable(activity: Activity, onShowAdCompleteListener: () -> Unit = {})
    val isAdAvailable: Boolean
}

@Singleton
class AppOpenAdManager @Inject constructor(
    @ApplicationContext private val context: Context
) : AppOpenAdManagerInterface {

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd: Boolean = false
    override var isAdAvailable: Boolean = false
        private set

    private var loadTime: Long = 0L
    private var lastActivityForShowAttempt: Activity? = null

    private var lastAdShownTime: Long = 0L
    private val MIN_INTERVAL_BETWEEN_ADS_MS: Long = 5 * 60 * 1000


    companion object {
        @Volatile
        var adJustDismissed = false
    }

    override fun loadAd() {
        if (isLoadingAd || isAdAvailable) {
            return
        }

        isLoadingAd = true
        val request = AdRequest.Builder().build()

        AppOpenAd.load(
            context,
            TEST_AD_UNIT_ID,
            request,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = System.currentTimeMillis()
                    isAdAvailable = true

                    if (ProcessLifecycleOwner.get().lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) &&
                        lastActivityForShowAttempt != null &&
                        !AppLifecycleObserver.isShowingAd) {
                        showAdIfAvailable(lastActivityForShowAttempt!!, onShowAdCompleteListener = {})
                    }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    appOpenAd = null
                    isLoadingAd = false
                    isAdAvailable = false
                }
            }
        )
    }

    override fun showAdIfAvailable(
        activity: Activity,
        onShowAdCompleteListener: () -> Unit
    ) {
        lastActivityForShowAttempt = activity

        if (AppLifecycleObserver.isShowingAd) {
            onShowAdCompleteListener()
            return
        }

        if (!isAdAvailable) {
            onShowAdCompleteListener()
            loadAd()
            return
        }

        if (!wasLoadTimeLessThanNHoursAgo(4)) {
            appOpenAd = null
            isAdAvailable = false
            onShowAdCompleteListener()
            loadAd()
            return
        }

        if (System.currentTimeMillis() - lastAdShownTime < MIN_INTERVAL_BETWEEN_ADS_MS) {
            onShowAdCompleteListener()
            if (!isAdAvailable) loadAd()
            return
        }


        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isAdAvailable = false
                AppLifecycleObserver.isShowingAd = false
                adJustDismissed = true
                onShowAdCompleteListener()
                loadAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isAdAvailable = false
                AppLifecycleObserver.isShowingAd = false
                onShowAdCompleteListener()
            }

            override fun onAdShowedFullScreenContent() {
                lastAdShownTime = System.currentTimeMillis()
                AppLifecycleObserver.appOpenAdShownThisSession = true
            }
        }

        AppLifecycleObserver.isShowingAd = true
        appOpenAd?.show(activity)
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        if (loadTime == 0L) return false
        val dateDifference: Long = System.currentTimeMillis() - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < (numMilliSecondsPerHour * numHours)
    }
}

