package com.hannapapova.counter.tools

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

internal fun userInputIsCorrect(name: String) = !(name.isBlank() || name.isEmpty())

internal fun fragmentHideKeyboard(context: Context, view: View) {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
