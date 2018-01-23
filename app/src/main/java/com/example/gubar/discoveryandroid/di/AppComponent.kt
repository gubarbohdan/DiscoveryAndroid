package com.example.gubar.discoveryandroid.di

import com.example.gubar.discoveryandroid.viewmodel.ClientsListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(clientsListViewModel: ClientsListViewModel)
}