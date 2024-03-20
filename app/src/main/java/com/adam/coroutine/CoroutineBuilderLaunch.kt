package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Coroutine Scope 안에서만 사용 가능
 *Caller Coroutine을 중단하지 않음
 *생성된 작업을 의미하는 Job을 리턴
 *비동기 작업 실행 요청하고, 자기 할 일 하는 경우에 주로 사용
 * */
fun main() = runBlocking {
    launch {
        delay(1000)
        println("A")
    }
    launch {
        delay(500)
        println("B")
    }
    println("C")
}