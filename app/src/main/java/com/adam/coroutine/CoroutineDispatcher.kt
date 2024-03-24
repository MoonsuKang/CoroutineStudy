package com.adam.coroutine

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineDispatcher: ViewModel() {

    // Coroutine Dispatcher with Coroutine Builder
    fun dispatcher_1() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                // Do Network I/O
                launch(Dispatchers.Main) {
                    // Do UI task
                }
            }
        }
    }
    // Coroutine Dispatcher with withContext
    fun dispatcher_2() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                // Do Network I/O
                return@withContext "data"
            }
            Log.d(TAG,"data: $data")
        }
    }
}