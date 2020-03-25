package com.eagledev.triphelper.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.eagledev.triphelper.FakeRepository
import com.eagledev.triphelper.domain.SaveTripUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class TripViewModelTest {

    // Subject under test
    private lateinit var tripViewModel: TripViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tripRepository: FakeRepository

    private lateinit var savedStateHandle: SavedStateHandle
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        @Before
        fun setUp() {
            Dispatchers.setMain(mainThreadSurrogate)

            tripRepository = FakeRepository()
//            tripViewModel = TripViewModel(savedStateHandle, tripRepository, SaveTripUseCase())
        }
    }

    @Test


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

}