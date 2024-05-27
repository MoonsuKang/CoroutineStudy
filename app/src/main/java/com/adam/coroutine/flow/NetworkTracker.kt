package com.adam.coroutine.flow

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * callbackFlow
 * callback은 suspend function이 아니기 때문에 emit을 호출할 수 없음
 * callbackFlow{ }를 통해 데이터를 보낼(trySend) 수 있는 블럭을 만들 수 있음
 * awaitClose{ }를 통해 flow가 종료되었을 때 실행할 코드 블럭을 등록할 수 있음
 * */
class NetworkTracker(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isAvailable = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                trySend(true)
            }

            override fun onUnavailable() {
                trySend(false)
            }
        }
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, callback)
        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }

}