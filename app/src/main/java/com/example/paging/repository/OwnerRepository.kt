package com.example.paging.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paging.api.ApiService
import com.example.paging.api.Owner
import com.example.paging.api.RetrofitClient
import com.example.paging.db.OwnerDao
import com.example.paging.db.OwnerDatabase
import com.example.paging.utils.INITIAL_LOAD_SIZE
import com.example.paging.utils.PAGE_SIZE
import com.example.paging.utils.PRE_FETCH_DISTANCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class OwnerRepository(application: Application) {
    private val ownerDao: OwnerDao = OwnerDatabase.getDatabase(application).ownerDao()
    var allOwnersLiveData: LiveData<PagedList<Owner>>
    private val apiService: ApiService = RetrofitClient.instance.apiService
    //private val dataSourceFactory: DataSource.Factory<Int, Owner>

    val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

    init {
        // Load Data from Room, and Room will decide whether to load data from local or from Network
        val dataSourceFactory = ownerDao.getAllOwnerDataSourceFactory()
        allOwnersLiveData = LivePagedListBuilder(dataSourceFactory, config)
                .setBoundaryCallback(ItemBoundaryCallback(apiService, this))
                .build()
    }

    fun insertAllContactToDb(vararg contacts: Owner): List<Long> {
        return runBlocking {
            async(Dispatchers.IO) {
                ownerDao.insertAll(*contacts)
            }.await()
        }
    }
}