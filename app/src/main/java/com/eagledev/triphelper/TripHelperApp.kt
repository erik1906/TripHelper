package com.eagledev.triphelper

import android.app.Activity
import android.app.Application
import com.eagledev.triphelper.di.AppInjector
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class TripHelperApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)

    }

    override fun activityInjector() = dispatchingAndroidInjector


}