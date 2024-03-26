package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 부모가 취소되면 자식에게도 취소가 전파된다.
 * 반대로 자식이 취소되도 부모는 영향을 받지 않는다.
 * */
fun main(): Unit = runBlocking {
    val parentJob = launch {
        println("[parent] started")
        launch {
            println("[child] started")
            for (i in 0..10) {
                delay(100)
                println("i: $i")
            }
            println("[parent] completed")
        }
    }
    delay(500)
    parentJob.cancel()
}