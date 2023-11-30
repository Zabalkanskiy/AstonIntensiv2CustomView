package com.seven.astonintensiv2customview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CustomViewViewModel(customViewRepository: CustomViewRepository): ViewModel() {
   private val repository = customViewRepository
    fun getAngleInRepository():LiveData<Float>{
        return repository.angle
    }

    fun setAngleInRepository(angle:Float){
        repository.angle.postValue(angle)
    }

    fun getCurrentAngle():Float = repository.currentAngle

    fun setCurrentAngle(currentAngle: Float) {
        repository.currentAngle = currentAngle
    }

    fun getFinalAngle():Float = repository.finalAngle

    fun setFinalAngle(finalAngle:Float){
        repository.finalAngle = finalAngle
    }

    fun getPlaceHolderUrl(): String = repository.urlPlaceHolder
}