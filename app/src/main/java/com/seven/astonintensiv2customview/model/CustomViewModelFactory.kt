package com.seven.astonintensiv2customview.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomViewModelFactory(private val customViewRepository: CustomViewRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomViewViewModel::class.java)) {
            return CustomViewViewModel(customViewRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
