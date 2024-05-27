package com.adam.coroutine.sharedresource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 *  Mutex를 사용하게 되면 구간에 대해서 다른 쓰레드의 접근을 막을 수 있음
 *  synchronized와 비슷하게 withLock을 사용할 수 있음
 *  synchronized에 비해 큰 장점은 경합이 발생한 경우 쓰레드를 블로킹 하는 것이 아니라 중단 시키는 것!
 * */

fun main(): Unit = runBlocking {
    val mutex = Mutex()
    var count = 0
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch {
        repeat(1000) {
            launch {
                repeat(1000) {
                    mutex.withLock {
                        count++
                    }
                }
            }
        }
    }
    job.join()
    println("count: ${count}")
}