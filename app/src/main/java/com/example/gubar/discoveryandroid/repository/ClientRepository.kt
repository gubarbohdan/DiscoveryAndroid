package com.example.gubar.discoveryandroid.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.db.DiscoveryDb
import com.example.gubar.discoveryandroid.retrofit.DiscoveryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientRepository @Inject constructor(
        private val discoveryDb: DiscoveryDb,
        private val discoveryApi: DiscoveryApi
) {
    fun getAllClients(): LiveData<List<Client>> {
        val clientObservable = discoveryApi.getClients()
        val clientsLiveData = MutableLiveData<List<Client>>()
        clientObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ clientsList ->
                    clientsLiveData.value = clientsList
                }, { t: Throwable? -> t!!.printStackTrace() })
        return clientsLiveData
    }

    fun getClientById(id: Long): LiveData<Client> {
        val clientObservable = discoveryApi.getClientById(id)
        val clientLiveData = MutableLiveData<Client>()
        clientObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({client ->
                    clientLiveData.value = client
                }, {t: Throwable? -> t!!.printStackTrace()})

        return clientLiveData
    }

}
