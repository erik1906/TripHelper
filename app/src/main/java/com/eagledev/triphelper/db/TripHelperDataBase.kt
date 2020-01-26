package com.eagledev.triphelper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eagledev.triphelper.model.Trip


@Database(
    entities = [Trip::class],
    version = 1,
    exportSchema = false
)
abstract class TripHelperDataBase: RoomDatabase() {

    abstract fun tripDao(): TripDao
}