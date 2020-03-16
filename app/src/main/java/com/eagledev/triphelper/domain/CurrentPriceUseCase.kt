package com.eagledev.triphelper.domain

import androidx.lifecycle.Transformations
import com.eagledev.triphelper.repositories.SettingsRepository
import javax.inject.Inject

open class CurrentPriceUseCase @Inject constructor(private val settingsRepository: SettingsRepository){

    open operator fun invoke() =
        Transformations.map(settingsRepository.observableSettings()){it.price}

}