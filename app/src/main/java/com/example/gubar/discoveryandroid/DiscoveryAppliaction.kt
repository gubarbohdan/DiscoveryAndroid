package com.example.gubar.discoveryandroid

import android.app.Application
import com.example.gubar.discoveryandroid.retrofit.DiscoveryApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gubar on 21.01.2018.
 */
class DiscoveryAppliaction : Application() {

    /**
     * Companion object to create the GithubApiService
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