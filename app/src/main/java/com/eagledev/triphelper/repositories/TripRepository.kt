package com.eagledev.triphelper.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.triphelper.db.TripDao
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.utils.TripSharedPreferences
import timber.log.Timber
import javax.inject.Inject

class TripRepository @Inject constructor(private val tripSharedPreferences: TripSharedPreferences,
                                         private val tripDao: TripDao){

    private val _price = MutableLiveData(tripSharedPreferences.getPrice())

    val price: LiveData<Int>
        get() = _price

    suspend fun getCurrent() =
        tripDao.getCurrent()


    suspend fun saveTrip(trip: Trip){
        val saved = tripDao.insertTrip(trip)
        Timber.tag("SavedState").d("Saved status =  $saved")
    }





}