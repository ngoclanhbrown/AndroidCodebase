package com.brown.codebase.utils

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment

private fun View.observeKeyboard(callback: (Boolean) -> Unit) {

    var isKeyboardShowing = false

    viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)

        val screenHeight = height
        //  rect.bottom is the position above soft keypad or device button.
        //  if keypad is shown, the rect.bottom is smaller than that before.
        val keypadHeight = screenHeight - rect.bottom

        //  0.15 ratio is perhaps enough to determine keypad height.
        if (keypadHeight > screenHeight * 0.15) {
            if (!isKeyboardShowing) {
                isKeyboardShowing = true
                callback(true)
            }
        } else {
            if (isKeyboardShowing) {
                isKeyboardShowing = false
                callback(false)
            }
        }
    }
}


fun Fragment.observeKeyboard(callback: (isOpen: Boolean) -> Unit) {
    view?.observeKeyboard(callback)
}


fun ComponentActivity.observeKeyboard(callback: (isOpen: Boolean) -> Unit) {
    val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
    rootView?.observeKeyboard(callback)
}
