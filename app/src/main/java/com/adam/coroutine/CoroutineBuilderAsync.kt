package com.adam.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Coroutine Scope 안에서만 사용 가능
 * Caller Coroutine을 중단하지 않음
 *생성되는 값을 가지고 올 수 있는 Deferred<T>를 Return
 * await()로 Coroutine을 중단시키면서 값을 기다릴 수 있다.
 * */

fun main() = runBlocking {
    val a = async {
        delay(1000)
        return@async "A"
    }
    val b = async {
        delay(1000)
        return@async "B"
    }
    println("C")
    println(a.await())
    println(b.await())
    println("D")
}
