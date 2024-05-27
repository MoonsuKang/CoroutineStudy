package com.adam.coroutine.flow

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

data class User(val name: String, val age: Int)

/**
 * Flow란?
 * → 리액티브 프로그래밍을 지원하기 위한 API로서, 데이터 스트림을 의미함
 * Reactive Programming?
 * 연속된 데이터의 변화를 관찰(Observer Pattern)하고
 * 변화된 데이터를 가공(Functional Programming)해서 지속적으로 전파하는 것이라고 생각하면 됨
 * */

// flowOf를 사용하여 Flow를 생성

fun main() = runBlocking {
    flowOf(
        User("Mike", 20),
        User("James", 25),
        User("Jack", 30),
    )
        .filter { it.age > 24 }
        .map { it.name }
        .collect { println(it) }
}