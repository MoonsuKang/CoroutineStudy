package com.adam.coroutine.sharedresource

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit


/**
 * Mutex와 비슷하게 Semaphore을 사용하면 구간에 대해서 다른 쓰레드의 접근을 막을 수 있음
 * 차이점은 접근 쓰레드를 여러개로 설정할 수 있음
 * Mutex의 withLock 비슷하게 withPermit을 사용할 수 있음
 * */
fun main(): Unit = runBlocking {
    val semaphore = Semaphore(1)
    var count = 0
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch {
        repeat(1000) {
            launch {
                repeat(1000) {
                    semaphore.withPermit {
                        count++
                    }
                }
            }
        }
    }
    job.join()
    println("count: $count")
}
