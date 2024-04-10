package com.adam.coroutine.exception

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Scope에 설정된 에러 핸들러에서 에러를 받아서 처리 가능
 *
 * */

fun main(): Unit {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("handler: Caught ex: $exception")
    }

    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch(handler) {
        throw Exception("error")
    }

    Thread.sleep(1000)

}