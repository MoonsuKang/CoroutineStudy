package com.adam.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 비동기 시나리오 - 쓰레드 바꿔야 할 경우1
 * withContext를 이용하면 작업을 중단하면서 다른 쓰레드 환경을 이용할 수 있다.
 */
fun main() = runBlocking {
    println("main started")

    val user = withContext(Dispatchers.IO) { loadUser() }
    println("user : $user")

    val product = withContext(Dispatchers.IO) { loadProduct() }
    println("product : $product")

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


