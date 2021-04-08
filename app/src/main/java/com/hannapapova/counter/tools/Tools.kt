package com.hannapapova.counter.tools

internal fun userInputIsCorrect(name: String) = !(name.isBlank() || name.isEmpty())
