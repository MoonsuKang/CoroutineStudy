import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 부모 Scope이 종료 or 취소되면 자동으로 내부의 모든 코루틴 역시 종료 or 취소
 *
 */

fun main() = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    coroutineScope.launch {
        launch {
            println("Started A")
            delay(500)
            println("Finished A")
        }
        launch {
            println("Started B")
            delay(500)
            println("Finished B")
        }
    }
    delay(100)
    coroutineScope.cancel()
}
