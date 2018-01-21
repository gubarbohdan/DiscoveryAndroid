package com.example.gubar.discoveryandroid.retrofit

import com.example.gubar.discoveryandroid.client.Client
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by gubar on 21.01.2018.
 */
interface DiscoveryApi {
    @GET("/clients")
    fun getClients() : Observable<List<Client>>
}