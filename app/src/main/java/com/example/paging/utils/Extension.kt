package com.example.paging.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

val PAGE_SIZE = 20
val INITIAL_LOAD_SIZE = 40
val PRE_FETCH_DISTANCE = 10
val INITIAL_PAGE = 1
val SITE_NAME = "stackoverflow";
val ROOT_TAG = "Example Paging ";

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> listToArray(list: List<*>): Array<T> {
    return (list as List<T>).toTypedArray()
}