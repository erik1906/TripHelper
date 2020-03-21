package com.eagledev.triphelper.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.eagledev.triphelper.FakeHistoryRepository
import com.eagledev.triphelper.FakeRepository
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.repositories.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class HistoryViewModelTest {

    // Subject under test
    private lateinit var historyViewModel: HistoryViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Use a fake repository to be injected into the viewmodel
    private lateinit var historyRepository: FakeHistoryRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        historyRepository = FakeHistoryRepository()

        historyViewModel = HistoryViewModel(historyRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun getTripList() {

        val passengerInfo = listOf(PassengerStatus("juan perez", true), PassengerStatus("Ernesto", true), PassengerStatus("Erde", false))

        val trip = Trip(id = 5, tripInfo = getTripInfo(), dateTime = getOffsetDateTime(), passengers = passengerInfo, active = true, currentPrice = 55)
        val trip2 = Trip(id = 6, tripInfo = getTripInfo(), dateTime = getOffsetDateTime(), passengers = passengerInfo, active = false, currentPrice = 60)
        runBlocking {
            launch (Dispatchers.Main){
                getValue(historyViewModel.tripList)[0]?.equals(trip)?.let { assert(it) }
                getValue(historyViewModel.tripList)[1]?.equals(trip2)?.let { assert(it) }
            }

        }



    }

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}