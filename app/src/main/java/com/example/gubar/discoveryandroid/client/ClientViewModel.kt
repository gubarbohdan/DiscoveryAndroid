package com.example.gubar.discoveryandroid.client

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.gubar.discoveryandroid.application.DiscoveryApplication
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.repository.ClientRepository
import javax.inject.Inject

/**
 * Created by bohdanhu on 25.01.18.
 */
class ClientViewModel : ViewModel() {

    @Inject
    lateinit var clientRepository: ClientRepository

    init {
        injectDagger()
    }

    private var client: LiveData<Client>? = null

    fun getClient(id: Long): LiveData<Client>? {
            client = MutableLiveData<Client>()
            client = clientRepository.getClientById(id)
        return client
    }

    private fun injectDagger() = DiscoveryApplication.appComponent.inject(this)

}