package com.adam.coroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Hot Stream
 * 빠르게 데이터를 발행. 구독자가 나타나면 발행 시작
 * 데이터를 발행하는 방식을 정의하고, 구독자가 나타나면 데이터를 생성해서 전달
 * */
fun main(): Unit = runBlocking {
    val flow = MutableStateFlow(0)
    launch {
        (1..4).forEach {
            delay(100)
            flow.value = it
        }
    }
    launch {
        flow.collect {
            println("1st: $it")
        }
    }
    launch {
        delay(2000)
        flow.collect {
            println("2nd: $it")
        }
    }
}