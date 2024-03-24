package com.adam.coroutine

import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.DisposableHandle
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext


/**
 *  코루틴의 생명주기는 Job이 관리
 *  Job은 CoroutineContext의 한 종류로서 CoroutineScope이 갖고 있다.
 *  Job은 부모와 자식이 있어서 구조적 동시성을 지원해준다.
 *
 * */

// job == CoroutineContext
interface Job : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Job>

    // 부모 자식 관계
    val parent: Job?
    val children: Sequence<Job>

    // 실행 상태
    val isActive: Boolean
    val isCompleted: Boolean
    val isCancelled: Boolean

    // 시작과 취소
    fun start(): Boolean
    fun cancel(cause: CancellationException? = null)

    fun invokeOnCompletion(handler: CompletionHandler): DisposableHandle //완료 콜백
}