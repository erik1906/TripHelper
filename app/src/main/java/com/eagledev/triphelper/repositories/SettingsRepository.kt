package com.eagledev.triphelper.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.triphelper.utils.Event
import com.eagledev.triphelper.utils.TripSharedPreferences
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SettingsRepository @Inject constructor(private val tripSharedPreferences: TripSharedPreferences){


    private val _price = MutableLiveData(tripSharedPreferences.getPrice())

    val price: LiveData<Float>
        get() = _price

    private val _seats = MutableLiveData(tripSharedPreferences.getSeats())

    val seats: LiveData<Int>
        get() = _seats

    val status = MutableLiveData<Event<Boolean>>()

    fun update(seats: Int, price: Float){
        try {
            tripSharedPreferences.setPrice(price)
            tripSharedPreferences.setSeats(seats)
            _seats.value = tripSharedPreferences.getSeats()
            _price.value = tripSharedPreferences.getPrice()
            status.value = Event(true)
        }catch (e: Exception){
            Timber.e(e)
            status.value = Event(false)
        }
    }
}