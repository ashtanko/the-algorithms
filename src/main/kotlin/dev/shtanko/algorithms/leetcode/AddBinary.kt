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
fun interface AddBinary {
    operator fun invoke(
        left: String,
        right: String,
    ): String
}

/**
 * Time complexity: O(max(N,M)), where N and M are lengths of the input strings a and b.
 * Space complexity: O(max(N,M)) to keep the answer.
 */
val addBinaryBitByBitComputation = AddBinary { left: String, right: String ->
    val sb = StringBuilder()
    var i: Int = left.lastIndex
    var j: Int = right.lastIndex
    var carry = 0
    while (i >= 0 || j >= 0) {
        var sum = carry
        if (j >= 0) {
            sum += right[j--] - '0'
        }
        if (i >= 0) {
            sum += left[i--] - '0'
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
val addBinaryBitManipulation = AddBinary { left: String, right: String ->
    when {
        left.isBlank() && right.isNotBlank() -> right
        left.isNotBlank() && right.isBlank() -> left
        left.isBlank() && right.isBlank() -> ""
        else -> {
            var firstNum = BigInteger(left, 2)
            var secondNum = BigInteger(right, 2)
            val zero = BigInteger.ZERO
            var carry: BigInteger
            var result: BigInteger
            while (secondNum != zero) {
                result = firstNum.xor(secondNum)
                carry = firstNum.and(secondNum).shiftLeft(1)
                firstNum = result
                secondNum = carry
            }
            firstNum.toString(2)
        }
    }
}
