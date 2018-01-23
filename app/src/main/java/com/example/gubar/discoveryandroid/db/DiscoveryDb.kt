package com.example.gubar.discoveryandroid.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = arrayOf(ClientEntity::class),
        version = 1
)
abstract class DiscoveryDb : RoomDatabase() {
    companion object {
        fun create(context: Context): DiscoveryDb {
            val databaseBuilder = Room.databaseBuilder(context, DiscoveryDb::class.java, "discovery.db")
            return databaseBuilder
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun clients(): ClientDao

}