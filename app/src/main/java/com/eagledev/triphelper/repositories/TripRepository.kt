package com.eagledev.triphelper.repositories

import com.eagledev.triphelper.db.TripDao
import com.eagledev.triphelper.model.Trip
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(private val tripDao: TripDao){

    suspend fun getCurrent() =
        tripDao.getCurrent()


    suspend fun saveTrip(trip: Trip): Boolean{

        return try {
            val tripRes = tripDao.insertTrip(trip)

            Timber.tag("cycle").d("Res = $tripRes Id : ${trip.id} ${trip.passengers }")
            tripRes !=  0L

        }catch (e: Exception){
            Timber.e(e)
            false
        }
    }



}