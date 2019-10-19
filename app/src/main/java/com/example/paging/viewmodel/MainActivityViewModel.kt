package com.example.paging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.paging.api.Owner
import com.example.paging.repository.OwnerRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var allOwnersLiveData: LiveData<PagedList<Owner>> = OwnerRepository(application).allOwnersLiveData
}