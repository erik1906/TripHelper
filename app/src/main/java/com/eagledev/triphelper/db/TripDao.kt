package com.eagledev.triphelper.db

import androidx.room.*
import com.eagledev.triphelper.model.Trip

@Dao
abstract class TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTrip(trip: Trip): Long

    @Query("SELECT * FROM trip")
    abstract suspend fun getTrips(): List<Trip>
    //Todo("Change this to pagination")

    @Query("SELECT * FROM trip WHERE id = :id")
    abstract suspend fun getTripById(id: Int): Trip

    @Update
    abstract suspend fun updateTrip(trip: Trip): Int

    @Query("SELECT * FROM trip WHERE active == 1")
    abstract suspend fun getCurrent(): Trip?
}