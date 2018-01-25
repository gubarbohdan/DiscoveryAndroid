package com.example.gubar.discoveryandroid.di

import com.example.gubar.discoveryandroid.client.ClientViewModel
import com.example.gubar.discoveryandroid.clientlist.ClientsListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(clientsListViewModel: ClientsListViewModel)
    fun inject(clientViewModel: ClientViewModel)

}