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

import dev.shtanko.algorithms.BYTE
import dev.shtanko.algorithms.DECIMAL
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale
import kotlin.math.abs

private const val WTF = 0xfffccccccccccccL
private const val LIM = 40
private const val SIZE_CHARACTERS = "KMGTPE"

/**
 * Converts bytes to human-readable string
 */
fun Long.toHumanReadableByteCountBin(): String {
    val bytes = this
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)
    if (absB < BYTE) {
        return String.format(Locale.getDefault(), "%d B", bytes)
    }
    var value = absB
    val ci: CharacterIterator = StringCharacterIterator(SIZE_CHARACTERS)
    var i = LIM
    while (i >= 0 && absB > WTF shr i) {
        value = value shr DECIMAL
        ci.next()
        i -= DECIMAL
    }
    value *= java.lang.Long.signum(bytes).toLong()
    return String.format(Locale.getDefault(), "%.1f %ciB", value / BYTE.toDouble(), ci.current())
}
