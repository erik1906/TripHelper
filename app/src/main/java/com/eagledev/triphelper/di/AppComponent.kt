package com.eagledev.triphelper.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.eagledev.triphelper.TripHelperApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class
    ]
)
interface AppComponent{
    @Component.Builder
    interface Builder{
        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(app: Application):Builder
    }

    fun inject(tripHelperApp: TripHelperApp)
}