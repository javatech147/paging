package com.example.paging.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paging.api.Owner
import com.example.paging.db.OwnerPageKeyedDataSourceFactory
import com.example.paging.utils.INITIAL_LOAD_SIZE
import com.example.paging.utils.PAGE_SIZE
import com.example.paging.utils.PRE_FETCH_DISTANCE

class OwnerRepository {
    var allOwnersLiveData: LiveData<PagedList<Owner>>

    val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

    init {
        // Load Data from data source factory
        val dataSourceFactory = OwnerPageKeyedDataSourceFactory()
        allOwnersLiveData = LivePagedListBuilder(dataSourceFactory, config)
                .build()
    }
}