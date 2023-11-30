package com.seven.astonintensiv2customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class RainbowViev @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val paint: Paint = Paint()
    var rotationAngle = 0f
    private var animator: ValueAnimator? = null

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    private val colors = listOf(
        Color.RED,
        Color.parseColor("#FFA500"),
        Color.YELLOW,
        Color.GREEN,
        Color.parseColor("#87CEFA"),
        Color.BLUE,
        Color.MAGENTA
    )
  //  private val paint = Paint().apply {
  //      style = Paint.Style.FILL
  //      isAntiAlias = true
  //  }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f

        canvas.rotate(rotationAngle, centerX, centerY)
     //   canvas.drawRect(centerX - 50, centerY - 50, centerX + 50, centerY + 50, paint)
        // val height = height
        val radius = width.coerceAtMost(height) / 2.toFloat()

        val sectorAngle = 360f / colors.size

        var currentAngle = 0f
        for (color in colors) {
            paint.color = color
            canvas.drawArc(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                currentAngle,
                sectorAngle,
                true,
                paint
            )
            currentAngle += sectorAngle
        }
    }







}