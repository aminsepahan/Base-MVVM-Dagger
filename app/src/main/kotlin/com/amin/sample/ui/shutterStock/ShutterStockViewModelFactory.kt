package com.amin.sample.ui.shutterStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShutterStockViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ShutterStockViewModel::class.java)) {
            return ShutterStockViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}