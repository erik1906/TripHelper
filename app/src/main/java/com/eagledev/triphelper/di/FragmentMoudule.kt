package com.eagledev.triphelper.di

import com.eagledev.triphelper.ui.TripFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeTripFragment(): TripFragment
}
