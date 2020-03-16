package com.eagledev.triphelper.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.triphelper.model.Settings
import com.eagledev.triphelper.utils.Event
import com.eagledev.triphelper.utils.TripSharedPreferences
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SettingsRepository @Inject constructor(private val tripSharedPreferences: TripSharedPreferences){


    private val _settings = MutableLiveData(Settings())

    private val settings: LiveData<Settings>
        get() = _settings


    val status = MutableLiveData<Event<Boolean>>()

    fun update(seats: Int, price: Int){
        try {
            tripSharedPreferences.setPrice(price)
            tripSharedPreferences.setSeats(seats)
            val newSettings = Settings(tripSharedPreferences.getSeats(), tripSharedPreferences.getPrice())
           _settings.value = newSettings
            status.value = Event(true)
            Timber.tag("settdebug").d("Settings ${settings.value}")

        }catch (e: Exception){
            Timber.e(e)
            status.value = Event(false)
        }
    }

    fun getSetting(): Settings =
        Settings(tripSharedPreferences.getSeats(), tripSharedPreferences.getPrice())

    fun observableSettings() =
        settings
}