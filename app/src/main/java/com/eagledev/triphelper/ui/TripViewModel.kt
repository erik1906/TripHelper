package com.eagledev.triphelper.ui

import androidx.lifecycle.*
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.model.TripInfo
import com.eagledev.triphelper.repositories.TripRepository
import com.eagledev.triphelper.utils.AssistedSavedStateViewModelFactory
import com.eagledev.triphelper.utils.Event
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import timber.log.Timber

const val IN_TRIP = "inTrip"
const val PASSENGERS = "passengers"
const val ESTIMATED = "estimated"
const val DATE = "date"
const val COUNT = "count"



class TripViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle, private val tripRepository: TripRepository): ViewModel() {

    private var id = 0
    private lateinit var day: OffsetDateTime
    private var count = 0

    private val _error = MutableLiveData<Event<Boolean>>()

    val error: LiveData<Event<Boolean>>
        get() = _error



    @AssistedInject.Factory
    interface Factory: AssistedSavedStateViewModelFactory<TripViewModel>{
        override fun create(savedStateHandle: SavedStateHandle): TripViewModel
    }

    fun start() {

        tripRepository.update()
        viewModelScope.launch(Dispatchers.Main){
            tripRepository.getCurrent()?.let {
                Timber.tag("cycle").d("Id : ${it.id} ${it.active }")
                id = it.id
                day = it.dateTime
                count = it.tripInfo.count
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
                val date = day.format(formatter)

                savedStateHandle.set(PASSENGERS, it.passengers)
                savedStateHandle.set(DATE, date)
                savedStateHandle.set(COUNT, it.tripInfo)
                savedStateHandle.set(IN_TRIP, it.active)
            }

        }
    }

    fun getDate() = savedStateHandle.getLiveData<String>(DATE)

    fun inTrip() = savedStateHandle.getLiveData<Boolean>(IN_TRIP)

    fun setTrip(inTrip: Boolean) {
        savedStateHandle.set(IN_TRIP, inTrip)
        if(inTrip){

            day = OffsetDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
            val date = day.format(formatter)
            savedStateHandle.set(DATE, date)
        }
    }

    fun getPassenger() = savedStateHandle.getLiveData<List<PassengerStatus>>(PASSENGERS)

    fun addPassenger(list: List<PassengerStatus>) {

        savedStateHandle.set(PASSENGERS, list)

    }

    fun getPassengerCount() = savedStateHandle.getLiveData<TripInfo>(COUNT)

    fun addPassenger() {

        if(count < tripRepository.seats.value ?: 0) {
            _error.value = Event(false)
            val current = savedStateHandle.getLiveData<TripInfo>(COUNT).value ?: TripInfo()
            val tripInfo = TripInfo(current.count + 1, (current.count + 1) * (tripRepository.price.value ?: 0))
            savedStateHandle.set(COUNT, tripInfo)
            count++
        }else{
            _error.value = Event(true)
        }

    }

    fun saveTrip(active: Boolean) {
        if(inTrip().value == true) {
            val trip = Trip(
                id = id,
                dateTime = day,
                tripInfo = savedStateHandle.getLiveData<TripInfo>(COUNT).value
                    ?: TripInfo(),
                passengers = savedStateHandle.getLiveData<List<PassengerStatus>>(PASSENGERS).value
                    ?: listOf(),
                active = active,
                currentPrice = tripRepository.price.value ?: 0
            )
            Timber.tag("cycle").d("Id saved Id: $id trip: ${trip.passengers}")
            GlobalScope.launch(Dispatchers.IO) {
                tripRepository.saveTrip(
                    trip
                )
            }
        }
    }

    fun clearTrip(){
        savedStateHandle.set(PASSENGERS, listOf<PassengerStatus>())
        savedStateHandle.set(DATE, "")
        savedStateHandle.set(COUNT, TripInfo())
        savedStateHandle.set(IN_TRIP, false)
        id = 0
        count = 0
    }





}
