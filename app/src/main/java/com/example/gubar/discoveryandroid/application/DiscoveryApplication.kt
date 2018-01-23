package com.example.gubar.discoveryandroid.application

import android.app.Application
import com.example.gubar.discoveryandroid.di.AppComponent
import com.example.gubar.discoveryandroid.di.AppModule
import com.example.gubar.discoveryandroid.di.DaggerAppComponent

/**
 * Created by bohdanhu on 23.01.18.
 */
class DiscoveryApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()
    }
}