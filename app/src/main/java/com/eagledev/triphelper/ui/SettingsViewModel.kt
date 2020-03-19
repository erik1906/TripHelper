package com.eagledev.triphelper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.eagledev.triphelper.domain.CurrentPriceUseCase
import com.eagledev.triphelper.domain.SeatCountUseCase
import com.eagledev.triphelper.repositories.SettingsRepository
import com.eagledev.triphelper.utils.Event
import java.lang.NumberFormatException
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository,
                                            currentPriceUseCase: CurrentPriceUseCase
): ViewModel() {


    val seats = Transformations.map(settingsRepository.observableSettings()){it.seats}

    val price = Transformations.map(settingsRepository.observableSettings()){it.price}

    val updateStatus = Transformations.map(settingsRepository.status){it}

    private val _empty = MutableLiveData<Event<Boolean>>()

    val empty: LiveData<Event<Boolean>>
    get() =
        _empty

    fun updateSetting(seats: Int, price: String){
        try {
            settingsRepository.update(seats, price.toInt())
        }catch (e:NumberFormatException){
            _empty.value = Event(true)
        }

    }
}
