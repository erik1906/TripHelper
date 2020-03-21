package com.eagledev.triphelper.repositories

import com.eagledev.triphelper.db.TripDao
import com.eagledev.triphelper.model.Trip
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


interface TripRepository {
    suspend fun getCurrent(): Trip?

    suspend fun saveTrip(trip: Trip): Boolean
}



@Singleton
class DefaultTripRepository @Inject constructor(private val tripDao: TripDao): TripRepository{

    override suspend fun getCurrent() =
        tripDao.getCurrent()


    override suspend fun saveTrip(trip: Trip): Boolean{

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