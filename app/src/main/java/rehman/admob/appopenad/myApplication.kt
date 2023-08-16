package rehman.admob.appopenad

import android.app.Application
import com.google.android.gms.ads.MobileAds

class myApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        AppOpenAdManager(this,"ca-app-pub-3940256099942544/3419835294")
    }

}