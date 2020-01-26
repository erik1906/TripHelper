package com.eagledev.triphelper.utils

import android.content.SharedPreferences

class TripSharedPreferences (private val sharedPreferences: SharedPreferences){

    fun setPrice(price: Float)=
        sharedPreferences.edit().putFloat("price", price).apply()


    fun getPrice()=
        sharedPreferences.getFloat("price", 0F)

    fun setSeats(seats: Int)=
        sharedPreferences.edit().putInt("seats", seats).apply()


    fun getSeats()=
        sharedPreferences.getInt("seats", 0)
}