package com.seven.astonintensiv2customview.model

import androidx.lifecycle.MutableLiveData

class CustomViewRepository {
    val angle = MutableLiveData<Float>()
    var currentAngle = 0f
    var finalAngle = 0f

    val urlPlaceHolder = "https://loremflickr.com/640/360"
}