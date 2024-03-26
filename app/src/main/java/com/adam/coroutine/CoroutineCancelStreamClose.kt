package com.adam.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileInputStream
import java.util.concurrent.CancellationException

/** 자원 해제 해야될 경우
 * 1. try finally
 * 2. use 함수
 * 3. invokeOnCompletion
 * 4. suspendCancellableCoroutine
 * 콜백 기반 API와 호환해야 할 때는 suspendCancellableCoroutine이 유용함
 *
 * */


/** 자원해제 - try finally
 * 취소가 요청되면 코루틴이 중단 되는 시점에 CancellationException이 발생함
 * 예외 처리로 finally 부분에 자원을 해제하면 됨
 * */

fun main1(): Unit = runBlocking {
    val job = launch(Dispatchers.IO) {
        val stream = FileInputStream("file")
        try {
            println("started")
            // Do Something with stream
            delay(1000) // 여기서 예외 발생
            println("completed")
        } finally {
            stream.close()
        }
    }
    delay(500)
    job.cancel()
}


/** Closable 인터페이스가 구현된 자원이라면 use {} 를 통해서 내부적으로 finally { close() } 호출이 가능함
 */
fun main2(): Unit = runBlocking {
    val job = launch(Dispatchers.IO) {
        FileInputStream("file").use {
            println("started")
            // Do Something with stream
            delay(1000) // 여기서 예외 발생
            println("completed")
        }
    }
    delay(500)
    job.cancel()
}


/** Job에 대해서 종료가 될 때 불리는 리스너를 등록할 수 있음 → invokeOnCompletion
 * 정상 종료와 취소 모두 불리우며 인자로 받는 예외가 무엇이냐로 취소인지 확인 할 수 있음
 */
fun main3(): Unit = runBlocking {
    val stream = FileInputStream("file")
    val job = launch(Dispatchers.IO) {
        println("started")
        // Do Something with stream
        delay(1000)
        println("completed")
    }
    job.invokeOnCompletion {
        if (it is CancellationException) {
            println("cancelled")
        }
        stream.close()
    }
    delay(500)
    job.cancel()
}

