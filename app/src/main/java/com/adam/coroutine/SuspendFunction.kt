package com.adam.coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
/**
 * 하나의 Thread에서 여러 Coroutine이 실행된다.
 * */
fun main() = runBlocking(CoroutineName("Coroutine #0")) {
    log("main Started")
    launch(CoroutineName("Coroutine #1")) {
        log("Before load")
        val data = loadFromBackground() // 비동기 로직의 결과값을 콜백 없이 리턴으로 받아 온다.
        log("data: $data")
    }
    launch(CoroutineName("Coroutine #2")) {
        for (i in 0..5){
            delay(200)
            log("i: $i")
        }
    }
    log("main Finished")
}

suspend fun log(message: String) {
    println("[${Thread.currentThread()}][${currentCoroutineContext()[CoroutineName]}] $message")
}

suspend fun loadFromBackground(): String {
    delay(1000)
    return "Suspend Function"
}
