package com.eagledev.triphelper.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.eagledev.triphelper.repositories.HistoryRepository
import javax.inject.Inject

class HistoryViewModel @Inject constructor(historyRepository: HistoryRepository): ViewModel() {

    val tripList = Transformations.map(historyRepository.getTripList()){it}


}
