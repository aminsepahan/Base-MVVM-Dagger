package com.amin.sample.ui.imgur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amin.sample.ui.shutterStock.ShutterStockViewModel

class ImgurViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ShutterStockViewModel::class.java)) {
            return ShutterStockViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}