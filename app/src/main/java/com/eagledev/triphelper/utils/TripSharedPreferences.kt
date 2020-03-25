package com.eagledev.triphelper.utils

import android.content.SharedPreferences

interface TripSharedPreferences{
    fun setPrice(price: Int)
    fun getPrice():Int
    fun setSeats(seats: Int)
    fun getSeats():Int
}

class DefaultTripSharedPreferences (private val sharedPreferences: SharedPreferences): TripSharedPreferences{

    override fun setPrice(price: Int)=
        sharedPreferences.edit().putInt("price", price).apply()


    override fun getPrice()=
        sharedPreferences.getInt("price", 0)

    override fun setSeats(seats: Int)=
        sharedPreferences.edit().putInt("seats", seats).apply()


    override fun getSeats()=
        sharedPreferences.getInt("seats", 1)
}