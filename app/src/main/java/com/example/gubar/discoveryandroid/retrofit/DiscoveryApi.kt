package com.example.gubar.discoveryandroid.retrofit

import com.example.gubar.discoveryandroid.client.Client
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by gubar on 21.01.2018.
 */
interface DiscoveryApi {
    @GET("/clients")
    fun getClients() : Call<List<Client>>
}