package com.eagledev.triphelper.domain

import androidx.lifecycle.MutableLiveData
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.repositories.TripRepository
import com.eagledev.triphelper.result.Result
import javax.inject.Inject

open class SaveTripUseCase @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase,
    private val tripRepository: TripRepository
){

    private val result = MutableLiveData<Result<Boolean>>()

    fun observe() = result

    open suspend operator fun invoke(trip: Trip) {
        result.postValue(Result.Loading)

        val setting = getSettingUseCase()
        trip.currentPrice = setting.price

        val res = tripRepository.saveTrip(trip)

        if(res){
            result.postValue(Result.Success(true))
        }else{
            result.postValue(Result.Error(Exception("Saved exception")))
        }
    }
}