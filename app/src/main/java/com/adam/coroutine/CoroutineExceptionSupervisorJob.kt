package com.adam.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 한 코루틴이 죽어도 다른 코루틴이 죽지않게 하려면?(코루틴 에러 전파범위 제어) -> SuperVisorJob() 사용
 * 아래와 같이 작성하면 다른 자식에게 전파는 하지않으나 launch자체 에러로 앱 종료가 된다.
 * */
fun main(): Unit {
    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    scope.launch {
        delay(500)
        throw Exception("Error")
    }
    scope.launch {
        delay(1000)
        throw Exception("After 1000ms")
    }
    Thread.sleep(2000)
}