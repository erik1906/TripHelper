package com.eagledev.triphelper.di

import androidx.lifecycle.ViewModel
import com.eagledev.triphelper.ui.TripViewModel
import com.eagledev.triphelper.utils.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes=[AssistedInject_BuilderModule::class])
abstract class BuilderModule {

/*    @Binds
    @IntoMap
    @ViewModelKey(SomeViewModel::class)
    abstract fun bindVMFactory(f: SomeViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>*/

    @Binds
    @IntoMap
    @ViewModelKey(TripViewModel::class)
    abstract fun bindTripViewModel (f: TripViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}