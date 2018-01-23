package com.example.gubar.discoveryandroid.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientEntity(
        @PrimaryKey

        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String)