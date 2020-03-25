package com.eagledev.triphelper

import com.eagledev.triphelper.utils.TripSharedPreferences

class FakeTripSharedPreferences: TripSharedPreferences{
    private var price: Int? = 55
    private var seats: Int? = 5

    override fun setPrice(price: Int) {
        this.price = price
    }

    override fun getPrice(): Int {
        return price ?: 0
    }

    override fun setSeats(seats: Int) {
        this.seats = seats
    }

    override fun getSeats(): Int {
        return seats ?: 0
    }

    fun setNullPrice(){
        price = null
    }

}