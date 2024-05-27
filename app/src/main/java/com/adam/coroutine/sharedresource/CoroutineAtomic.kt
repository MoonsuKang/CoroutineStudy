package com.adam.coroutine.sharedresource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

/**
 * 공유 자원에 대한 안전한 접근을 위해서는 Atomic을 사용해야 함
 * Atomic 객체는 연산을 할 때 여러 쓰레드가 충돌해도 원자성을 보장해줄 수 있음.
 * CoroutineScope에서 실행될 수 있고, 쓰레드간 블로킹이 발생하지 않기 때문에 간단한 공유자원인 경우 권장을 한다.
 * 여러 라인을 블록 단위로 막아주는건 아니기 때문에 복잡한 로직은 원자성을 보조 할 수 없음.
* */
fun main(): Unit = runBlocking {
    val count = AtomicInteger(0)
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch {
        repeat(1000) {
            launch {
                repeat(1000) {
                    count.incrementAndGet()
                }
            }
        }
    }
    job.join()
    println("count: ${count}")

}