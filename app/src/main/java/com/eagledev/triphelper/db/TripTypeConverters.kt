package com.eagledev.triphelper.db

import androidx.room.TypeConverter
import com.eagledev.triphelper.model.Passenger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.OffsetDateTime


object TripTypeConverters {

    private val gson = Gson()
    inline  fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)


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
    fun tostringList(value: String?): List<Passenger>?{
        if(value == null){
            return null
        }
        return gson.fromJson<List<Passenger>>(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringList(value: List<Passenger>?): String? {
        if(value == null){
            return null
        }
        return gson.toJson(value)
    }
}