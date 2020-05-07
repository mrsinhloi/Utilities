@file:Suppress("unused")
package com.sonhvp.utilities.standard

import android.widget.EditText

fun CharSequence.isBlankOrEmpty(): Boolean = isBlank() || isEmpty()
fun CharSequence.isNotBlankAndNotEmpty(): Boolean = isNotBlank() && isNotEmpty()

fun EditText.isBlankOrEmpty(): Boolean = text.run { isBlank() || isEmpty() }

fun EditText.trimmedString(): String = text.toString().trim()


fun String.removeHyphen(): String = this.replace("-", "")
fun String.removeSpace(): String = this.replace(" ", "")