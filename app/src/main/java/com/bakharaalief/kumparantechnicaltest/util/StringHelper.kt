package com.bakharaalief.kumparantechnicaltest.util

import java.util.*

object StringHelper {
    fun String.titleCase(): String =
        split(" ").joinToString(" ") { it.lowercase().capitalized() }

    fun String.capitalized(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }
}