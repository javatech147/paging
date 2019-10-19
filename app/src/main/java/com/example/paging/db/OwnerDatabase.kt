package com.example.paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.paging.api.Owner

@Database(entities = arrayOf(Owner::class), version = 1, exportSchema = false)
abstract class OwnerDatabase : RoomDatabase() {
    abstract fun ownerDao(): OwnerDao

    companion object {
        private var mDBInstance: OwnerDatabase? = null
        fun getDatabase(context: Context): OwnerDatabase {
            val tempInstance = mDBInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OwnerDatabase::class.java,
                        "owner_database"
                ).fallbackToDestructiveMigration().build()
                mDBInstance = instance
                return instance
            }
        }
    }
}