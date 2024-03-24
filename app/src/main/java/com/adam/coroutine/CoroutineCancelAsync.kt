package com.adam.coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * async의 경우 코루틴을 만들면서 해당하는 Deferred<T>를 리턴한다.
 * Deferred는 Job의 자식 인터페이스
 * Deferred를 통해 취소 가능
 * await() 중에 취소되면 해당 지점에서 취소 에러 발생 (JobCancellationException)
 *
 */
fun main(): Unit = runBlocking {
    val asyncJob: Deferred<String> = async {
        println("started")
        delay(5000)
        println("completed")
        return@async "data"
    }
    launch {
        delay(1000)
        asyncJob.cancel()
    }
    asyncJob.await()
}