package com.eagledev.triphelper.di

import androidx.lifecycle.ViewModelProvider
import com.eagledev.triphelper.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


}