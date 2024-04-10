package com.adam.coroutine.exception

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 취소와 마찬가지로 에러가 발생하면 자식 코루틴들은 취소 된다.
 * 취소와 다르게 에러가 발생하면 부모 코루틴도 취소가 됨 → 취소 요청을 받은 부모 코루틴은 다시 모든 자식 코루틴(결과적으로 형제 코루틴)을 취소 함
 * 결과적으로 계층 구조 내 모든 코루틴에 에러가 전파 됨
 *
 * */
fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(500)
            println("After 1000ms")
        }
        launch {
            delay(1000)
            throw Exception()
        }
        launch {
            delay(1500)
            println("After 1500ms")
        }
    }
    launch {
        delay(2000)
        println("After 2000ms")
    }
}