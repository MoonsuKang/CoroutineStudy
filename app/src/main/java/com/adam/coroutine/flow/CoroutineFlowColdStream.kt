package com.adam.coroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Cold Stream
 * 느리게 데이터를 발행. 굳이 요청하지 않으면 발행 시작 안함
 * 데이터를 발행하는 방식을 정의하고, 실제 구독자가 나타나면 데이터를 생성해서 전달
 * */
fun main(): Unit = runBlocking {
    val flow = flow {
        (1..4).forEach {
            delay(100)
            emit(it)
        }
    }
    launch {
        flow.collect {
            println("1st: $it")
        }
    }
    launch {
        delay(1000)
        flow.collect {
            println("2nd: $it")
        }
    }
}