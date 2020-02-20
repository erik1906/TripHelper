package com.eagledev.triphelper.di

import android.app.Application
import androidx.room.Room
import com.eagledev.triphelper.db.TripDao
import com.eagledev.triphelper.db.TripHelperDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule{

    @Singleton
    @Provides
    fun provideTripDatabase(app: Application): TripHelperDataBase{
        return Room.databaseBuilder(app, TripHelperDataBase::class.java, "TripHelper.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTripDao(tripDatabase: TripHelperDataBase): TripDao{
        return  tripDatabase.tripDao()
    }
}