package com.eagledev.triphelper.repositories

import androidx.paging.toLiveData
import com.eagledev.triphelper.db.TripDao
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val tripDao: TripDao){

    fun getTripList() = tripDao.getTrips().toLiveData(pageSize = 20)
}