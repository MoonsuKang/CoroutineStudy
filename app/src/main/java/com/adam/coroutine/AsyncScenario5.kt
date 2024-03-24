package com.adam.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 비동기 시나리오 - 쓰레드 바꿔야 할 경우2
 * async를 이용하면 작업을 중단하지 않고 다른 쓰레드 환경을 이용할 수 있다.
 * launch도 가능하다.
 */
fun main() = runBlocking {
    println("main started")

    val user = async(Dispatchers.IO) { loadUser() }
    println("user loading requested")
    val product = async(Dispatchers.IO) { loadProduct() }
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


