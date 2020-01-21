package com.eagledev.triphelper.di

import com.eagledev.triphelper.ui.HistoryFragment
import com.eagledev.triphelper.ui.SettingsFragment
import com.eagledev.triphelper.ui.TripFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeTripFragment(): TripFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

}
