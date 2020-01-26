package com.eagledev.triphelper.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.eagledev.triphelper.db.TripTypeConverters
import org.threeten.bp.OffsetDateTime

@TypeConverters(TripTypeConverters::class)
@Entity(tableName = "trip")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateTime: OffsetDateTime ,
    val price: Double = 0.0,
    val passengers: List<Passenger>? = null
)