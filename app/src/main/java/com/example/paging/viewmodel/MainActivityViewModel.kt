package com.example.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.paging.api.Owner
import com.example.paging.repository.OwnerRepository

class MainActivityViewModel : ViewModel() {
    var allOwnersLiveData: LiveData<PagedList<Owner>> = OwnerRepository().allOwnersLiveData
}