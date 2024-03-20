package com.adam.coroutine


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
/**
 * CoroutineContext로 작업 내용을 작성해서 CoroutineScope을 생성
 * 생성 로직을 보면 Job은 무조건 Context에 존재하도록 설정
 *
 *
 * */
fun main() {
    CoroutineScope(EmptyCoroutineContext).launch{
        // Bouded to Here
    }
}
//private fun CoroutineScope(context: CoroutineContext): CoroutineScope =
//    ContextScope(if (context[Job] != null) context else context + Job())