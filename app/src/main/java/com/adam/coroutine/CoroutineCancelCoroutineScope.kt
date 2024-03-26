package com.adam.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 정의된 scope의 모든 코루틴을 취소시킬 수 있다.
 * scope는 자신 내부에 등록된 Job을 탐색해서 취소를 요청한다.
 * */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        println("started")
        delay(1000)
        println("completed")

    }
    delay(100)
    scope.cancel()
}