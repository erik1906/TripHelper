package com.eagledev.triphelper.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.eagledev.triphelper.model.Settings
import com.eagledev.triphelper.repositories.SettingsRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SeatCountUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {


    private val result = MediatorLiveData<Int>()
    open operator fun invoke(): MediatorLiveData<Int> {
        Transformations.map(settingsRepository.observableSettings()) {
            Timber.tag("settdebug").d("Settings Seat use case ${it.seats}")

            result.postValue(it.seats)
            it
        }

        return result
    }

    fun observe(): MediatorLiveData<Int> {
        return result
    }
}
