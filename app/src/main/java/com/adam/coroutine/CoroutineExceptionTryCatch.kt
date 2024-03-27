package com.adam.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 기본적인 예외처리 try-catch
 * 에러가 발생할 수 있는 로직을 try-catch구문으로 감싸서 처리
 * */
fun main(): Unit {
    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        try {
            throw Exception("error")
        } catch (ex: Exception) {
            println("Caught ex: $ex")
        }
    }

    Thread.sleep(1000)
}
