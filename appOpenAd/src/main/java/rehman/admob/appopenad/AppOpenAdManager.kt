package rehman.admob.appopenad

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd

class AppOpenAdManager(private val application: Application, adID: String) : LifecycleObserver,
    Application.ActivityLifecycleCallbacks {

    companion object {
        private const val LOG_TAG = "AppOpenAdManager"
    }

    private var appOpenAd: AppOpenAd? = null
    private var isShowingAds = false
    private var AD_UNIT_ID = adID

    private var loadCallback: AppOpenAd.AppOpenAdLoadCallback? = null

    private var currentActivity: Activity? = null

    init {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfAvailable()
    }

    /**
     * Request an ad
     */
    fun fetchAd() {
        if (isAdAvailable()) {
            return
        }
        loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(@NonNull appOpenAd: AppOpenAd) {
                super.onAdLoaded(appOpenAd)
                this@AppOpenAdManager.appOpenAd = appOpenAd
                Log.d(LOG_TAG, "onAdLoaded")
            }

            override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Log.d(LOG_TAG, "onAdFailedToLoad: ${loadAdError.message}")
            }
        }
        val adRequest = getAdRequest()
        AppOpenAd.load(
            application,
            AD_UNIT_ID,
            adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            loadCallback as AppOpenAd.AppOpenAdLoadCallback
        )
    }

    fun showAdIfAvailable() {
        Log.d(LOG_TAG, "showAdIfAvailable")
        if (!isShowingAds && isAdAvailable()) {
            val fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    isShowingAds = true
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    this@AppOpenAdManager.appOpenAd = null
                    isShowingAds = false
                    fetchAd()
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }
            }
            appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
            currentActivity?.let { appOpenAd?.show(it) }
        } else {
            fetchAd()
        }
    }

    /**
     * Creates and returns ad request.
     */
    private fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */
    fun isAdAvailable(): Boolean {
        return appOpenAd != null
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }
}
