package com.adam.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * async의 경우 블록 안에서 예외가 발생하면 이걸 즉시 처리하는게 아니라
 * 나중에 await가 불리는 시점에서 처리하는거
 * try-catch를 await() 시점에 사용하면 됨
 * */
suspend fun main(): Unit {
    val scope = CoroutineScope(Dispatchers.Default)

    val deferred = scope.async {
        throw Exception("error")
    }

    try {
        deferred.await()
    } catch (ex: Exception) {
        println("ex: $ex")
    }
}