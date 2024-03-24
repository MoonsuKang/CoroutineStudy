package com.adam.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 비동기 시나리오 - 여러 작업을 동시에 수행하고 기다리기
 * async로 코루틴을 생성해서 작업을 시작하고 await를 이용해서 main코루틴을 작업이 완료될때까지 중단시킴
 */
fun main() = runBlocking {
    println("main started")

    val user = async { loadUser() }
    println("user loading requested")
    val product = async { loadProduct() }
    println("product loading requested")

    println("user : ${user.await()}")
    println("product : ${product.await()}")
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


