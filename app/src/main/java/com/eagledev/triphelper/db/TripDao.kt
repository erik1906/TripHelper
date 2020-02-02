package com.eagledev.triphelper.db

import androidx.paging.DataSource
import androidx.room.*
import com.eagledev.triphelper.model.Trip


@Dao
abstract class TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTrip(trip: Trip): Long

    @Query("SELECT * FROM trip ORDER BY id DESC")
    abstract fun getTrips(): DataSource.Factory<Int, Trip>

    @Query("SELECT * FROM trip WHERE id = :id")
    abstract suspend fun getTripById(id: Int): Trip

    @Update
    abstract suspend fun updateTrip(trip: Trip): Int

    @Query("SELECT * FROM trip WHERE active == 1")
    abstract suspend fun getCurrent(): Trip?
}