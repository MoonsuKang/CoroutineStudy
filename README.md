### **코루틴이란?**

안드로이드에서 간단하게 비동기적으로 코드를 실행할 수 있는 동시성 디자인 패턴

**그럼 여기서 비동기 프로그래밍이란?**
Synchronous(동기)는 작업을 순서대로 실행하는 것
Asynchronous(비동기)는 순서와 무관하게 작업을 실행하는 것
그리고 또한 **Blocking** 과 **NonBlocking**을 이해하고 있어야된다.

Blocking은 Caller가 Callee를 호출하면서 작업을 멈추고 대기하는 것
NonBlocking은 Caller는 Callee를 호출하고 대기하지 않고 바로 다음 작업을 수행하는 것

Synchronous - Blocking은 작업의 실행순서 == 완료순서
Asynchronous - Non-Blocking은 작업의 실행순서 ≠ 완료순서. 왜? 작업을 요청하고 바로 다른 일 시작해서.

### **쓰레드 방식의 비동기 프로그래밍**
쓰레드 == 작업을 처리하는 단위

**쓰레드 방식의 문제점**

자원의 효율성측면
- 쓰레드 생성과 ContextSwitching은 비용이 크다.(특히 메모리나 CPU소모가 큼)
- Blocking작업을 수행하면 쓰레드는 대기하면서 자원을 소모한다.

코드 관리
- 예외 처리 및 취소가 쉽지 않다.
- 쓰레드 간의 데이터를 소통하기 어렵다.
- 가독성이 좋지 않다.

**쓰레드 방식의 문제를 개선한거 == 코루틴**
자원의 효율성 측면
- 코루틴은 중단 메커니즘을 이용해 쓰레드가 대기하지 않고 계속 일을 할 수 있도록 한다.

코드 관리
- 쓰레드 방식의 정 반대라고 생각하면 된다.

**runBlocking**
- 코루틴을 만드는 Coroutine Builder
- Caller Thread를 코루틴이 끝날 때 까지 Blocking 한다.
- Coroutine Scope 밖에서 사용이 가능하다.
- main함수와 같이 특별한 경우에서만 사용한다.

**launch**
- 코루틴을 만드는 Coroutine Builder
- Coroutine Scope 안에서만 사용 가능하다.
- Caller Coroutine을 중단하지 않는다.

**suspend fun**
- Coroutine Scope 안에서만 사용 가능하다.
- Caller Coroutine을 중단시키고 자기 작업을 수행한다.


**Coroutine Context**
- Coroutine Scope 내부에 Coroutine Context가 존재한다.
- 컨테스트 정보들은 LinkedList 형태로 연결되어 있다.

**CoroutineScope는 어떻게 작성하는가?**

미리정의된 Scope
- GlobalScope - 앱의 생명주기와 함께하는 스콥
- lifeCycleScope - 컴포넌트 라이프사이클에 해당되는 스콥
- viewModelScope - 뷰모델 라이프사이클에 해당되는 스콥

커스텀 Scope
- CoroutineScope 함수를 이용해서 직접 생성

**코루틴을 생성하는 방법 - CoroutineBuilder**

CoroutineScope은 작업에 대한 정보와 범위의 명세
→ scope정보를 기반으로 실제 코루틴 메모리에 올리려면 Coroutine Builder를 사용한다.
[ 주요 API ]
- runBlocking - 특수한 경우에만 사용
- launch - fire and forgot
- async - fire and wait


**비동기 로직을 간결하게 작성하는 방법 → Suspend Function**

Suspend?
- 중단, 재개가 가능한 함수
- Thread Blocking 없이 다른 작업을 기다릴 수 있다.
- Suspend Function 혹은 Coroutine에서만 호출이 가능핟.

**다른 쓰레드를 이용하고 싶다면 → Coroutine Dispatcher**

코루틴을 어느 쓰레드로 보내서(Dispatch) 실행할지를 정의
[주요 Dispatcher]
- Dispatchers.Main : 주로 UI작업을 수행할 때 사용한다.
- Dispatchers.IO : I/O 작업을 수행하기 위해 사용한다.
- Dispatchers.Default : CPU 직얍적 작업을 위해 사용한다.
- Dispatchers.Unconfined : 호출한 쓰레드에서 이어서 작업을 하기 위해 사용한다.

주요 사용방법?
코루틴 빌더에 Coroutine context로 추가한다.
- 코루틴 빌더는 보통 Coroutine context를 인자로 받는다.
  - Coroutine context에는 Dispatcher도 포함된다.
- withContext을 사용한다.
    - 중단 함수이면서 내부 Block을 원하는 Dispatcher에서 실행하고 결과를 받아올 수 있는 API
    - 해당 코루틴에서 다른 쓰레드로 전환해서 작업을 하고 오고 싶을 때 사용

### **코루틴 취소**

작업은 때때로 취소가 가능해야 된다.
- ex1) 화면을 종료해서 더이상 API로 부터 데이터를 받아올 필요가 없는 경우
- ex2) 시간이 비정상적으로 오래 걸려서 작업을 진행할 수 없는 경우

취소를 하지 않으면 사용하지 않는 메모리와 CPU 낭비가 있다.
코루틴의 실행 취소는 CoroutineContext의 한 종류인 Job이 관리한다.
