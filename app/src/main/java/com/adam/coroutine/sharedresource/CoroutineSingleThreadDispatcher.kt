package com.adam.coroutine.sharedresource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * 여러 쓰레드 경합이 문제의 원인이므로 하나의 쓰레드에서만 코루틴이 실행되도록 하면 해결이 됨
 * 하지만, 여러 쓰레를 쓰지 못하기 때문에 성능적인 한계가 있음
 *
 * */

fun main(): Unit = runBlocking {
    var count = 0
    val singleDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val scope = CoroutineScope(singleDispatcher)
    val job = scope.launch {
        repeat(1000) {
            launch {
                repeat(1000) {
                    count++
                }
            }
        }
    }
    job.join()
    println("count: ${count}")
}