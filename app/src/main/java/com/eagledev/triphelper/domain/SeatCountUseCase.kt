package com.eagledev.triphelper.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.eagledev.triphelper.model.Settings
import com.eagledev.triphelper.repositories.SettingsRepository
import timber.log.Timber
import javax.inject.Inject

open class SeatCountUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {


    private val result = MutableLiveData<Int>()
    open operator fun invoke(): LiveData<Settings> {
        val trans = Transformations.map(settingsRepository.observableSettings()) {
            Timber.tag("settdebug").d("Settings Seat use case ${it.seats}")

            result.postValue(it.seats)
            it
        }

        return trans
    }

    fun observe(): LiveData<Int> {
        Timber.tag("settdebug").d("Settings observing")
        return result
    }
}
