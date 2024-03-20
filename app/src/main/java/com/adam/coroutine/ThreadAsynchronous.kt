package com.adam.coroutine

import kotlin.concurrent.thread


/**
 * 쓰레드 방식의 비동기 프로그래밍
 *
 * */
fun main(){
    thread(name = "쓰레드1") {
        thread(name = "쓰레드2") {
            val user = fetchUser()
            println(user)
        }
        thread(name = "쓰레드3") {
            val product = fetchProduct()
            println(product)
        }
    }
}
// fetchUser
fun fetchUser(): String {
    Thread.sleep(1000) // 네트워크 요청을 모방
    return "User Moonsu"
}

// fetchProduct
fun fetchProduct(): String {
    Thread.sleep(1000) // 네트워크 요청을 모방
    return "Product MacBook"
}