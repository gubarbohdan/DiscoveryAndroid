package com.example.gubar.discoveryandroid.retrofit

import com.example.gubar.discoveryandroid.data.Client
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by gubar on 21.01.2018.
 */
interface DiscoveryApi {
    @GET("/clients")
    fun getClients() : Observable<List<Client>>

    @GET("/clients/{id}")
    fun getClientById(@Path("id") id: Long) : Observable<Client>

    @POST("/clients")
    fun createClient(@Body client: Client) : Observable<Client>



    /**
     * Companion object to create the DiscoveryApi
     */
    companion object RetrofitFactory {

        val BASE_URL = "http://192.168.0.78:8080"

        fun getRetrofitApi(): DiscoveryApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(DiscoveryApi::class.java);
        }
    }
}