package com.adam.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking{
    launch {
        wait1000ms()
        println("World")
    }
    println("Hello")
}

suspend fun wait1000ms() {
    delay(1000)
}