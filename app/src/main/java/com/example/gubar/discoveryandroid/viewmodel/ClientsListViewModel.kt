package com.example.gubar.discoveryandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.gubar.discoveryandroid.application.DiscoveryApplication
import com.example.gubar.discoveryandroid.client.Client
import com.example.gubar.discoveryandroid.repository.ClientRepository
import javax.inject.Inject

class ClientsListViewModel : ViewModel() {

    @Inject
    lateinit var clientRepository: ClientRepository

    private var clients: LiveData<List<Client>>? = null

    init {
        injectDagger()
    }

    fun loadClients(): LiveData<List<Client>>? {
        if (clients == null) {
            clients = MutableLiveData<List<Client>>()
            clients = clientRepository.getAllClients()
        }
        return clients
    }

    private fun injectDagger() = DiscoveryApplication.appComponent.inject(this)
}