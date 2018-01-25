package com.example.gubar.discoveryandroid.data

/**
 * Created by gubar on 21.01.2018.
 */
data class Client (val id: Long, val firstName: String, val lastName: String, val email: String, val trips: List<Trip>)