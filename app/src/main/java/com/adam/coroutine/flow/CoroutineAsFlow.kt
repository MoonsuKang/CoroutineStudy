package com.adam.coroutine.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


/**
 * asFlow를 사용하여 리스트를 Flow로 변환.
 * */
fun main() = runBlocking {
    listOf(
        User("Mike", 20),
        User("James", 25),
        User("Jack", 30)
    ).asFlow()
        .filter { it.age > 24 }
        .map { it.name }
        .collect { println(it) }
}