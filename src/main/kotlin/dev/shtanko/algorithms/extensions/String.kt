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
package dev.shtanko.algorithms.extensions

import kotlin.math.min

private val alphabet = charArrayOf(
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z'
)

/**
 * Check is a string binary.
 */
fun String.isBinary(): Boolean {
    if (this.isBlank()) return false
    val set = toSet().toMutableSet().apply {
        removeAll(listOf('0', '1'))
    }
    return set.isEmpty()
}

/**
 * @return common prefix of two strings
 */
fun Pair<String, String>.commonPrefix(): String {
    val (left, right) = this
    val min = min(left.length, right.length)
    for (i in 0 until min) {
        if (left[i] != right[i]) return left.substring(0, i)
    }
    return left.substring(0, min)
}

/**
 * Count zeroes in binary string
 * @return int array of zeroes
 */
fun String.countZeroesOnes(): IntArray {
    val c = IntArray(2)
    for (element in this) {
        c[element - '0']++
    }
    return c
}

fun String.getNumberOfLetter(): Int {
    val sb = StringBuilder()
    for (c in this) {
        sb.append("${alphabet.indexOf(c)}")
    }
    return sb.toString().removeZeroesInBegin().toInt()
}

fun String.removeZeroesInBegin(): String {
    if (this.isEmpty()) {
        return ""
    }
    if (this.length == 1 && this == "0") {
        return this
    }
    if (this.toCharArray().first().isZero().not()) {
        return this
    }
    if (this.isAllZeroes()) {
        return "0"
    }
    return this.toInt().toString()
}

fun String.isAllZeroes(): Boolean {
    return this.none { it.isZero().not() }
}

fun Char.isZero(): Boolean {
    return this == '0'
}
