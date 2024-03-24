package com.adam.coroutine

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *  launch의 경우 코루틴을 만들면서 해당하는 Job을 리턴
 *  이때 얻은 Job을 통해 취소 가능
 */
fun main(): Unit = runBlocking {
    val launchJob: Job = launch {
        println("started")
        delay(100)
        println("completed")
    }
    delay(100)
    launchJob.cancel()
}