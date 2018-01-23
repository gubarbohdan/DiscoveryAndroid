package com.example.gubar.discoveryandroid.di

import android.content.Context
import com.example.gubar.discoveryandroid.application.DiscoveryApplication
import com.example.gubar.discoveryandroid.db.DiscoveryDb
import com.example.gubar.discoveryandroid.retrofit.DiscoveryApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val discoveryApplication: DiscoveryApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = discoveryApplication

    @Provides
    @Singleton
    fun provideRoomDiscoveryDataSource(context: Context) =
            DiscoveryDb.create(context)

    @Provides
    @Singleton
    fun provideRetrofit() = DiscoveryApi.getRetrofitApi()

}