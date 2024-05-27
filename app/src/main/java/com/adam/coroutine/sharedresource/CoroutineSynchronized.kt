package com.adam.coroutine.sharedresource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 코루틴 역시 여러 쓰레드에서 작업 단위가 동시에 실행되는 구조이기 때문에 공유 자원에 대해서 동기화가 필요하다
 *아래 함수는 1000000번 count가 오르기를 의도하지만 실제로는 여러 코루틴이 여러 쓰레드에서 하나의 count 변수를 접근하면서 ++ 연산이 제대로 되지 않게 된다.
fun main(): Unit = runBlocking {
var count = 0
val scope = CoroutineScope(Dispatchers.Default)
val job = scope.launch {
repeat(1000) {
launch {
repeat(1000) {
count++
}
}
}
}
job.join()
println("count: $count++")
}
 * */


/**
 * 결국 여러 쓰레드에서 변수 접근 부분을 동시에 사용하지 못하게 하면 되는 것이니, synchronized를 사용할 수 있음
 * synchronized는 lock을 통해서 한 번에 단 하나의 쓰레드만 내부 블럭을 실행하도록 함
 * 하지만 synchronized는 블럭 내부에서 중단 함수를 콜 할 수 없고, 다른 쓰레드가 블로킹이 되버릴 수 있기 때문에 효율적이지 못 함
 *
 * */

fun main(): Unit = runBlocking {
    val lock = Any()
    var count = 0
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch {
        repeat(1000) {
            launch {
                repeat(1000) {
                    synchronized(lock) {
                        count++
                    }
                }
            }
        }
    }
    job.join()
    println("count: $count")
}

