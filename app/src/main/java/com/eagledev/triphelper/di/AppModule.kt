package com.eagledev.triphelper.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.eagledev.triphelper.utils.TripSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, BuilderModule::class, RoomModule::class])

class AppModule{
    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

    @Singleton
    @Provides
    fun providesTripSharedPreferences(sharedPreferences: SharedPreferences) =
        TripSharedPreferences(sharedPreferences)

}