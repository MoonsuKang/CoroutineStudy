package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 특수한 빌더로서 Coroutine Scope 밖에서 사용
 *Caller Thread를 Blocking 함
 *주로 main이나 Test에서 사용
 * */
fun main() {
    runBlocking {
        delay(1000)
        println("runBlocking is finished")

    }
    println("main is finished")
}