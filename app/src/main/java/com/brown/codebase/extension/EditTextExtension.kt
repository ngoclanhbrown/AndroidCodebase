package com.brown.codebase.extension

import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Request focus and show the soft keyboard, use it in almost cases
 */
fun EditText.showKeyboard(imm: InputMethodManager) {
    requestFocus()
    if (hasWindowFocus()) {
        //  No need to wait for the window to get focus
        showKeyboardWhenFocused(imm)
    } else {
        //  We need to wait until the window gets focus
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (hasFocus) {
                        this@showKeyboard.showKeyboardWhenFocused(imm)
                        //  Remove the listener once we are done
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            }
        )
    }
}


/**
 * Show the soft keyboard, use it when [EditText] already has focus
 */
private fun EditText.showKeyboardWhenFocused(imm: InputMethodManager) {
    if (isFocused) {
        post {
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
