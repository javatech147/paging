package com.example.paging.api

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner_table")
data class Owner(
        @PrimaryKey(autoGenerate = false)
        @NonNull
        //@ColumnInfo(name = "user_id")
        val user_id: Long,

        //@ColumnInfo(name = "display_name")
        val display_name: String,

        //@ColumnInfo(name = "reputation")
        val reputation: Long,

        //@ColumnInfo(name = "profile_image")
        val profile_image: String
)