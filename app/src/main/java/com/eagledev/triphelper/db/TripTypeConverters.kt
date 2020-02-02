package com.eagledev.triphelper.db

import androidx.room.TypeConverter
import com.eagledev.triphelper.model.Passenger
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.model.TripInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.OffsetDateTime


object TripTypeConverters {

    private val gson = Gson()
    private inline  fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)


    private val formatter = org.threeten.bp.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String): OffsetDateTime{
        return formatter.parse(value, OffsetDateTime::from)
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun toPassengerStatusList(value: String?): List<PassengerStatus>?{
        if(value == null){
            return null
        }
        return gson.fromJson<List<PassengerStatus>>(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromPassengerStatusList(value: List<PassengerStatus>?): String? {
        if(value == null){
            return null
        }
        return gson.toJson(value)
    }


    @TypeConverter
    @JvmStatic
    fun toTripInfo(value: String): TripInfo{
        return gson.fromJson<TripInfo>(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromTripInfo(value: TripInfo?): String? {
        return gson.toJson(value)
    }
}