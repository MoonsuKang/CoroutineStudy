package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * 시간제한을 두고 취소하고 싶으면 withTimeout이 유용하다.
 * suspend fun 으로서 입력 받은 Block의 결과를 기다렸다가 리턴해준다.
 * 시간이 초과되면 CancellationException의 한 종류인 TimeoutCancellationException을 던진다.
 *
 * */
fun main(): Unit = runBlocking {
    val data = withTimeout(1000){
    println("started")
    delay(5000)
    println("completed")
    return@withTimeout "data"
    }
    println("data: $data")
}