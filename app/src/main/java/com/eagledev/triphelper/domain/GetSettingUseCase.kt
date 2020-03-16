package com.eagledev.triphelper.domain

import com.eagledev.triphelper.repositories.SettingsRepository
import javax.inject.Inject


open class GetSettingUseCase @Inject constructor( private val settingsRepository: SettingsRepository) {

    open operator fun invoke() =
         settingsRepository.getSetting()

}