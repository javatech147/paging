package com.example.paging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.R
import com.example.paging.utils.ROOT_TAG
import com.example.paging.utils.log
import com.example.paging.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = ROOT_TAG + MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagedListAdapter = OwnerPagedListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = pagedListAdapter

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.allOwnersLiveData.observe(this, Observer {
            log(TAG, "Owner list size : ${it.size}")
            pagedListAdapter.submitList(it)
        })
    }
}
