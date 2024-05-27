package com.adam.coroutine.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.Date

/**
 * flow{ } 를 통해 데이터를 방출(emit)할 수 있는 블럭을 만들 수 있음
 * 블럭 안에서 emit(data)를 이용해서 data를 생산
 * collect는 연결된 Flow가 종료될 때 혹은 해당 코루틴이 종료될 때까지 유지
 * */
fun main() {
    val dataFlow = flow {
        while (true) {
            emit(Date())
            delay(1000)
        }
    }
    CoroutineScope(Dispatchers.Default).launch {
        dataFlow.collect {
            println("Date : $it")
        }
    }

    Thread.sleep(5000)
}