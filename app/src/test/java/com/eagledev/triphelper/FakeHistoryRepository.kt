package com.eagledev.triphelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.repositories.HistoryRepository
import com.eagledev.triphelper.ui.getOffsetDateTime
import com.eagledev.triphelper.ui.getTripInfo
import com.eagledev.triphelper.ui.mockPagedList

class FakeHistoryRepository: HistoryRepository{

    override fun getTripList(): LiveData<PagedList<Trip>> {
        val passengerInfo = listOf(PassengerStatus("juan perez", true), PassengerStatus("Ernesto", true), PassengerStatus("Erde", false))

        val trip = Trip(id = 5, tripInfo = getTripInfo(), dateTime = getOffsetDateTime(), passengers = passengerInfo, active = true, currentPrice = 55)
        val trip2 = Trip(id = 6, tripInfo = getTripInfo(), dateTime = getOffsetDateTime(), passengers = passengerInfo, active = false, currentPrice = 60)

        return MutableLiveData(mockPagedList(listOf(trip, trip2)))
    }


}