package com.eagledev.triphelper.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.eagledev.triphelper.db.TripDao
import com.eagledev.triphelper.model.Trip
import javax.inject.Inject


interface HistoryRepository{
    fun getTripList(): LiveData<PagedList<Trip>>
}
class DefaultHistoryRepository @Inject constructor(private val tripDao: TripDao): HistoryRepository{
    override fun getTripList() = tripDao.getTrips().toLiveData(pageSize = 20)

}