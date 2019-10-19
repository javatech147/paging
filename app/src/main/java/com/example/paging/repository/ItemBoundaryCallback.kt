package com.example.paging.repository

import androidx.paging.PagedList
import com.example.paging.api.ApiService
import com.example.paging.api.Owner
import com.example.paging.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ItemBoundaryCallback(
        private val network: ApiService,
        private val repository: OwnerRepository
) : PagedList.BoundaryCallback<Owner>() {

    companion object {
        private val TAG = ROOT_TAG + ItemBoundaryCallback::class.java.simpleName
    }

    private var pageToLoad = 1
    private var isRequestInProgress = false

    init {
        pageToLoad = PagingPreferences.getLastPage()
        log(TAG, "Page to load $pageToLoad")
    }

    // called when the last item in the database is loaded
    override fun onItemAtEndLoaded(itemAtEnd: Owner) {
        super.onItemAtEndLoaded(itemAtEnd)
        log(TAG, "onItemAtEndLoaded")
        requestAndSaveData()
    }

    //called when the first item in the database is loaded
    override fun onItemAtFrontLoaded(itemAtFront: Owner) {
        super.onItemAtFrontLoaded(itemAtFront)
        log(TAG, "onItemAtFrontLoaded")
    }

    // Called when there is no items in the Database.
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        log(TAG, "onZeroItemsLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return
        log(TAG, "requestAndSaveData")
        CoroutineScope(Dispatchers.IO).launch {
            isRequestInProgress = true
            val response = network.getOwnerList(pageToLoad, PAGE_SIZE, SITE_NAME)
            log(TAG, "Requested Url ${response.raw().request().url()}")
            if (response.isSuccessful) {
                log(TAG, "Response is successful")
                val ownerList = arrayListOf<Owner>()
                val itemList = response.body()!!.items
                for (item in itemList) {
                    ownerList.add(item.owner)
                }
                insertResultIntoDb(ownerList)
                pageToLoad++

                PagingPreferences.saveLastPage(pageToLoad)
                isRequestInProgress = false
            } else {
                isRequestInProgress = false
                log(TAG, "Response is Failure with Status Code : ${response.code()} Response : ${response.errorBody()!!.string()}")
            }
        }
    }

    private fun insertResultIntoDb(ownerList: ArrayList<Owner>) {
        log(TAG, "Inserting Page number $pageToLoad Records Into Database")
        val contactsArray = listToArray<Owner>(ownerList)
        val insertedContacts = repository.insertAllContactToDb(*contactsArray)
        log(TAG, "${insertedContacts.size} records Inserted Into Database")
    }
}