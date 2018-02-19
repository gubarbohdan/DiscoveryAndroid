package com.example.gubar.discoveryandroid.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.data.Hotel
import com.example.gubar.discoveryandroid.db.DiscoveryDb
import com.example.gubar.discoveryandroid.retrofit.DiscoveryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by bohdanhu on 12.02.18.
 */
class HotelRepository @Inject constructor(
        private val discoveryDb: DiscoveryDb,
        private val discoveryApi: DiscoveryApi
) {
    private val hotelsList = MutableLiveData<List<Hotel>>()

    fun getAllClients(): LiveData<List<Hotel>> {
        val hotelObservable = discoveryApi.getHotels()
        hotelObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ hotelList ->
                    hotelsList.value = hotelList
                }, { t: Throwable? -> t!!.printStackTrace() })
        return hotelsList
    }
}