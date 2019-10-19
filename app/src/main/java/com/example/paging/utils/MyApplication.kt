package com.example.paging.utils

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        lateinit var app: Context
    }

    override fun onCreate() {
        super.onCreate()
        app = this.applicationContext
    }
}