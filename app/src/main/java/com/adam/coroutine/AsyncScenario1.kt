package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 비동기 시나리오 - 여러 작업을 순서대로 실행하기
 * suspend 함수를 이용해서 작업을 요청하고 중단했다가 끝나면 재개하는 식으로 순서를 보장
 *
 * */
fun main() = runBlocking{
    println("main started")
    val user = loadUser()
    println("user: $user")
    val product = loadProduct()
    println("product = $product")
    println("main finished")
}

private suspend fun loadProduct(): String {
    delay(1000)
    return "product"
}

private suspend fun loadUser(): String {
    delay(1000)
    return "user"
}


