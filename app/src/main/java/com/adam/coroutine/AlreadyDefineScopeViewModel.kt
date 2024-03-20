package com.adam.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AlreadyDefineScopeViewModel: ViewModel() {
    fun scopeTest() {
        viewModelScope.launch{
            // Bounded to View Model
        }
    }
}