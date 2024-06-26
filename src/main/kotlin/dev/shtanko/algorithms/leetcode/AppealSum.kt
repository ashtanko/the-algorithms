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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.Constants.ALPHABET_LETTERS_COUNT

/**
 * 2262. Total Appeal of A String
 * @link https://leetcode.com/problems/total-appeal-of-a-string/
 * Functional interface representing an appeal sum operation on a String.
 */
fun interface AppealSum {
    /**
     * Performs an appeal sum operation on the given String.
     *
     * @param str The input String to perform the appeal sum on.
     * @return The result of the appeal sum operation.
     */
    operator fun invoke(str: String): Long
}

/**
 * Performs the appeal sum operation on the given String using dynamic programming.
 *
 * @param str The input String to perform the appeal sum on.
 * @return The result of the appeal sum operation.
 */
val appealSumDp = AppealSum { str: String ->
    var cur: Long = 0
    val prev = LongArray(ALPHABET_LETTERS_COUNT)
    str.mapIndexed { index, c ->
        cur += index + 1 - prev[c - 'a']
        prev[c - 'a'] = index.toLong() + 1
        cur
    }.sum()
}
