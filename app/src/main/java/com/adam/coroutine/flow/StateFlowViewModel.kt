package com.adam.coroutine.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * MutableStateFlow로 초기값 생성
 * asStateFlow()로 불변성 부여
 * update로 값 갱신
 * collectAsState로 값 수집
 * */
class StateFlowViewModel: ViewModel() {
    private val _username = MutableStateFlow("Unknown")
    val username = _username.asStateFlow()

    init {
        viewModelScope.launch {
            _username.update { loadUserName() }
        }
    }

    private suspend fun loadUserName(): String {
        delay(2000)
        return "userName"
    }
}