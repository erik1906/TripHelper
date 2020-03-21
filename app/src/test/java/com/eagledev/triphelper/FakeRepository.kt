package com.eagledev.triphelper

import com.eagledev.triphelper.model.Passenger
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.model.TripInfo
import com.eagledev.triphelper.repositories.TripRepository
import com.eagledev.triphelper.ui.getOffsetDateTime
import com.eagledev.triphelper.ui.getTripInfo
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

/**
 * Test repository for [TripRepository]
 */
class FakeRepository: TripRepository {
    override suspend fun getCurrent(): Trip? {
        val passengerInfo = listOf(PassengerStatus("juan", true), PassengerStatus("Ernesto", true), PassengerStatus("Erde", false))
        return Trip(id = 5, tripInfo = getTripInfo(), dateTime = getOffsetDateTime(), passengers = passengerInfo, active = true, currentPrice = 55)
    }

    override suspend fun saveTrip(trip: Trip): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}