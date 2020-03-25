package com.eagledev.triphelper.domain

import com.eagledev.triphelper.FakeSettingRepository
import com.eagledev.triphelper.FakeTripSharedPreferences
import com.eagledev.triphelper.model.Settings
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CurrentPriceUseCaseTest {

    private val tripSharedPreferences = FakeTripSharedPreferences()
    private val settingsRepository = FakeSettingRepository(tripSharedPreferences)
    private val currentPriceUseCase = CurrentPriceUseCase(settingsRepository)


    @Test
    fun testCurrentPrice(){
        tripSharedPreferences.setPrice(5)
        assert(currentPriceUseCase() == 5)
    }

    @Test
    fun testNullCurrentPrice(){
        tripSharedPreferences.setNullPrice()
        assert(currentPriceUseCase() == 0)
    }
}