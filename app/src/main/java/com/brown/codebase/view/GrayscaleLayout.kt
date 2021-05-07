package com.brown.codebase.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.brown.codebase.R

class GrayscaleLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var grayscaleEnable = false
        set(value) {
            field = value
            requestLayout()
        }

    private val paint = Paint()

    init {
        context.withStyledAttributes(attrs, R.styleable.GrayscaleLayout) {
            grayscaleEnable = getBoolean(R.styleable.GrayscaleLayout_grayscaleEnable, false)
        }

        val cm = ColorMatrix(GRAY_COLOR_MATRIX)
        paint.colorFilter = ColorMatrixColorFilter(cm)
    }


    override fun dispatchDraw(canvas: Canvas?) {
        if (grayscaleEnable) {
            canvas?.saveLayer(null, paint)
        }
        super.dispatchDraw(canvas)
        if (grayscaleEnable) {
            canvas?.restore()
        }
    }


    companion object {
        private val GRAY_COLOR_MATRIX = floatArrayOf(
            0.3F, 0.59F, 0.11F, 0F, 0F,
            0.3F, 0.59F, 0.11F, 0F, 0F,
            0.3F, 0.59F, 0.11F, 0F, 0F,
            0F, 0F, 0F, 1F, 0F
        )
    }

}
