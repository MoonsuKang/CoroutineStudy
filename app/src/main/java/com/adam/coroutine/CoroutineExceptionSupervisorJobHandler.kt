package com.adam.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 에러가 사라지는게 아니라 이걸 적절하게 할 Handler가 필요한 거
 * Handler가 에러난 launch를 잡아 핸들링하고, 나머지 잡은 정상 수행
 *
 * */
fun main(): Unit {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("handler : Caught ex: $exception")
    }
    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob() + handler)
    scope.launch {
        delay(500)
        throw Exception("Error")
    }
    scope.launch {
        delay(1000)
        throw Exception("After 1000ms")
    }
    Thread.sleep(2000)
}