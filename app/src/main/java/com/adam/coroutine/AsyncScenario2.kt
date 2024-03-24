package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 비동기 시나리오 - 여러 작업 동시에 수행
 * launch를 활용해서 다른 코루틴을 생성하고 작업을 수행
 */
fun main() = runBlocking{
    println("main started")
    launch {
        val user = loadUser()
        println("user: $user")
    }
    launch {
        val product = loadProduct()
        println("product: $product")
    }
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


