package com.adam.coroutine.flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * MutableStateFlow로 초기값 생성
 * asStateFlow()로 불변성 부여
 * update로 값 갱신
 * collectAsState로 값 수집
 * */
//class StateFlowActivity: ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            val viewModel = viewModel<StateFlowViewModel>()
//            val userName by viewModel.username.collectAsState()
//
//            Text(text = "User Name: $userName")
//        }
//    }
//}