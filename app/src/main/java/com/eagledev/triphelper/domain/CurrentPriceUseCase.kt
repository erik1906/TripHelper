package com.eagledev.triphelper.domain

import androidx.lifecycle.Transformations
import com.eagledev.triphelper.repositories.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrentPriceUseCase @Inject constructor(private val settingsRepository: SettingsRepository){

    open operator fun invoke() =
        settingsRepository.getSetting().price

}