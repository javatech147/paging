package com.example.paging.db

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.paging.api.Owner

class OwnerPageKeyedDataSourceFactory : DataSource.Factory<Int, Owner>() {
    val ownerDataSourceLiveData = MutableLiveData<OwnerPageKeyedDataSource>()
    override fun create(): DataSource<Int, Owner> {
        val latestDataSource = OwnerPageKeyedDataSource()
        ownerDataSourceLiveData.postValue(latestDataSource)
        return latestDataSource
    }
}