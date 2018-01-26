package com.example.gubar.discoveryandroid.data

/**
 * Created by gubar on 21.01.2018.
 */
data class Client (var id: Long? = null, val firstName: String, val lastName: String, val email: String, var trips: List<Trip>? = null)