package com.example.paging.utils

import android.content.Context
import android.content.SharedPreferences

class PagingPreferences {

    companion object {
        val PREF_FILE_NAME = "paging_perferences"
        val LAST_PAGE = "last_page"
        private val sharedPreferences: SharedPreferences = MyApplication.app.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPreferences.edit()

        fun saveLastPage(lastPage: Int) {
            editor.putInt(LAST_PAGE, lastPage).commit()
        }

        fun getLastPage(): Int {
            return sharedPreferences.getInt(LAST_PAGE, 1)
        }
    }
}