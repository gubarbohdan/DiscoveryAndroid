package com.example.gubar.discoveryandroid.hotel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.gubar.discoveryandroid.application.DiscoveryApplication
import com.example.gubar.discoveryandroid.data.Hotel
import com.example.gubar.discoveryandroid.repository.HotelRepository
import javax.inject.Inject

/**
 * Created by bohdanhu on 12.02.18.
 */
class HotelListViewModel : ViewModel() {

    @Inject
    lateinit var hotelRepository: HotelRepository

    private var hotels: LiveData<List<Hotel>>? = null

    init {
        injectDagger()
    }

    fun loadHotels(): LiveData<List<Hotel>>? {
        if (hotels == null) {
            hotels = MutableLiveData<List<Hotel>>()
            hotels = hotelRepository.getAllClients()
        }
        return hotels
    }

    private fun injectDagger() = DiscoveryApplication.appComponent.inject(this)
}