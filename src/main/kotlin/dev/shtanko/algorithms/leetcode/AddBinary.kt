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

import java.math.BigInteger

/**
 * 67. Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
 * @link https://leetcode.com/problems/add-binary/
 */
fun interface AddBinaryStrategy {
    operator fun invoke(
        a: String,
        b: String,
    ): String
}

/**
 * Time complexity: O(max(N,M)), where N and M are lengths of the input strings a and b.
 * Space complexity: O(max(N,M)) to keep the answer.
 */
val addBinaryBitByBitComputation = AddBinaryStrategy { a: String, b: String ->
    val sb = StringBuilder()
    var i: Int = a.lastIndex
    var j: Int = b.lastIndex
    var carry = 0
    while (i >= 0 || j >= 0) {
        var sum = carry
        if (j >= 0) {
            sum += b[j--] - '0'
        }
        if (i >= 0) {
            sum += a[i--] - '0'
        }
        sb.append(sum % 2)
        carry = sum / 2
    }
    if (carry != 0) {
        sb.append(carry)
    }
    sb.reverse().toString()
}

/**
 * Time complexity : O(N+M), where N and M are lengths of the input strings a and b.
 * Space complexity: O(max(N,M)) to keep the answer.
 */
val addBinaryBitManipulation = AddBinaryStrategy { a: String, b: String ->
    when {
        a.isBlank() && b.isNotBlank() -> b
        a.isNotBlank() && b.isBlank() -> a
        a.isBlank() && b.isBlank() -> ""
        else -> {
            var x = BigInteger(a, 2)
            var y = BigInteger(b, 2)
            val zero = BigInteger("0", 2)
            var carry: BigInteger
            var answer: BigInteger
            while (y.compareTo(zero) != 0) {
                answer = x.xor(y)
                carry = x.and(y).shiftLeft(1)
                x = answer
                y = carry
            }
            x.toString(2)
        }
    }
}
