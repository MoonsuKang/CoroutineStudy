package com.adam.coroutine

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlreadyDefineScope : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        GlobalScope.launch {
            // Bounded to Application LifeCycle
        }
        lifecycleScope.launch {
            // bounded to Component LifeCycle
        }
    }
}