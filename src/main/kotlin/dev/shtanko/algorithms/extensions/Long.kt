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

import dev.shtanko.algorithms.DECIMAL

/**
 * Check self is a super palindrome
 */
fun Long.isSuperPalindrome(): Boolean {
    var x = this
    var isSuper = false
    for (i in 0 until 2) {
        isSuper = x.isPalindrome()
        x /= 2
        if (isSuper.not()) return false
    }
    return isSuper
}

/**
 * Check self is a palindrome
 */
fun Long.isPalindrome(): Boolean {
    return this == this.reverse()
}

/**
 * Reverse self
 */
fun Long.reverse(): Long {
    var ans = 0L
    var x = this
    while (x > 0) {
        ans = DECIMAL * ans + x % DECIMAL
        x /= DECIMAL
    }
    return ans
}
