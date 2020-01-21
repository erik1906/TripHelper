package com.eagledev.triphelper.di

import androidx.lifecycle.ViewModel
import com.eagledev.triphelper.ui.HistoryViewModel
import com.eagledev.triphelper.ui.SettingsViewModel
import com.eagledev.triphelper.ui.TripViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

   @Binds
   @IntoMap
   @ViewModelKey(TripViewModel::class)
   abstract fun bindTripViewModel (viewModelClass: TripViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(HistoryViewModel::class)
   abstract fun bindHistoryViewModel (viewModelClass: HistoryViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(SettingsViewModel::class)
   abstract fun bindSettingsViewModel (viewModelClass: SettingsViewModel): ViewModel

}