/*
 * MIT License

 * Copyright (c) 2022 Oleksii Shtanko

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
package dev.shtanko.algorithms.utils

import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale

private const val WTF_IN = -999950
private const val WTF_OUT = 999950
private const val SIZE_CHARACTERS = "kMGTPE"
private const val ONE_T = 1000

/**
 * Converts bytes to human-readable string
 */
fun Long.toHumanReadableByteCountSI(): String {
    var bytes = this
    if (-ONE_T < bytes && bytes < ONE_T) {
        return "$bytes B"
    }
    val ci: CharacterIterator = StringCharacterIterator(SIZE_CHARACTERS)
    while (bytes <= WTF_IN || bytes >= WTF_OUT) {
        bytes /= ONE_T
        ci.next()
    }
    return String.format(Locale.getDefault(), "%.1f %cB", bytes / ONE_T.toDouble(), ci.current())
}
