package com.example.paging.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging.api.Owner

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // Insert List of Records into Room
    fun insertAll(vararg contactList: Owner): List<Long>

    @Query("SELECT * FROM owner_table ORDER BY reputation DESC")
    fun getAllOwnerDataSourceFactory(): DataSource.Factory<Int, Owner>
}