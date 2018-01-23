package com.example.gubar.discoveryandroid.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ClientDao {

    @Insert
    fun insert(clients: List<ClientEntity>)

    @Query("SELECT * FROM clients ORDER BY lastName ASC")
    fun getAllUsers(): DataSource.Factory<Int, ClientEntity>
}