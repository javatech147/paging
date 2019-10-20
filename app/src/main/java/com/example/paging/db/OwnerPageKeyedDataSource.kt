package com.example.paging.db

import androidx.paging.PageKeyedDataSource
import com.example.paging.api.Owner
import com.example.paging.api.RetrofitClient
import com.example.paging.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OwnerPageKeyedDataSource : PageKeyedDataSource<Int, Owner>() {

    companion object {
        private val TAG = ROOT_TAG + OwnerPageKeyedDataSource::class.java.simpleName
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Owner>) {
        log(TAG, "loadInitial")
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.instance.apiService.getOwnerList(INITIAL_PAGE, PAGE_SIZE, SITE_NAME)
            log(TAG, "Request Url : ${response.raw().request().url()}")
            if (response.isSuccessful) {
                val ownerList = arrayListOf<Owner>()
                val itemList = response.body()!!.items
                for (item in itemList) {
                    ownerList.add(item.owner)
                }
                callback.onResult(ownerList, null, INITIAL_PAGE + 1)
            } else {
                log(TAG, "Response is Failure with Status Code : ${response.code()} Response : ${response.errorBody()!!.string()}")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Owner>) {
        log(TAG, "loadAfter")
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.instance.apiService.getOwnerList(params.key, PAGE_SIZE, SITE_NAME)
            log(TAG, "Request Url : ${response.raw().request().url()}")
            if (response.isSuccessful) {
                val ownerList = arrayListOf<Owner>()
                val itemList = response.body()!!.items
                for (item in itemList) {
                    ownerList.add(item.owner)
                }
                val key = if (response.body()!!.has_more) (params.key + 1) else null
                callback.onResult(ownerList, key)
            } else {
                log(TAG, "Response is Failure with Status Code : ${response.code()} Response : ${response.errorBody()!!.string()}")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Owner>) {
        log(TAG, "loadBefore")
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.instance.apiService.getOwnerList(params.key, PAGE_SIZE, SITE_NAME)
            log(TAG, "Request Url : ${response.raw().request().url()}")
            if (response.isSuccessful) {
                val ownerList = arrayListOf<Owner>()
                val itemList = response.body()!!.items
                for (item in itemList) {
                    ownerList.add(item.owner)
                }
                val key = if (params.key > 1) params.key - 1 else null
                callback.onResult(ownerList, key)
            } else {
                log(TAG, "Response is Failure with Status Code : ${response.code()} Response : ${response.errorBody()!!.string()}")
            }
        }
    }
}