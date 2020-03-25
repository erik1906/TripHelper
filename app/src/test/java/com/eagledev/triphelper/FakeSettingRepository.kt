package com.eagledev.triphelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.triphelper.model.Settings
import com.eagledev.triphelper.repositories.SettingsRepository
import com.eagledev.triphelper.utils.Event

class FakeSettingRepository(private val tripSharedPreferences: FakeTripSharedPreferences): SettingsRepository {
    override val status = MutableLiveData<Event<Boolean>>()

    override fun update(seats: Int, price: Int) {
        TODO("Not yet implemented")
    }

    override fun getSetting(): Settings =
        Settings(tripSharedPreferences.getSeats(), tripSharedPreferences.getPrice())


    override fun observableSettings(): LiveData<Settings> {
        TODO("Not yet implemented")
    }

}