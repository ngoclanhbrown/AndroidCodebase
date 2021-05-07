package com.brown.codebase.extension

import android.graphics.Rect
import android.view.View

/**
 * Shortcut to use [View.post]
 */
inline fun View.doOnPost(
    crossinline action: (View) -> Unit
) {
    post {
        action.invoke(this)
    }
}


fun View.getLocationOnScreen(): IntArray {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return location
}


fun View.getDrawingRectOnScreen(): Rect {
    val location = IntArray(2)
    val outRect = Rect()
    this.getDrawingRect(outRect)
    this.getLocationOnScreen(location)
    outRect.offset(location[0], location[1])
    outRect.right = (outRect.left + outRect.width() * scaleX).toInt()
    outRect.bottom = (outRect.top + outRect.height() * scaleY).toInt()
    return outRect
}
