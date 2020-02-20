package com.eagledev.triphelper.utils

import android.content.SharedPreferences

class TripSharedPreferences (private val sharedPreferences: SharedPreferences){

    fun setPrice(price: Int)=
        sharedPreferences.edit().putInt("price", price).apply()


    fun getPrice()=
        sharedPreferences.getInt("price", 0)

    fun setSeats(seats: Int)=
        sharedPreferences.edit().putInt("seats", seats).apply()


    fun getSeats()=
        sharedPreferences.getInt("seats", 1)
}