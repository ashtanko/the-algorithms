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
package dev.shtanko.algorithms.math

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.pow

interface NarcissisticNumber {
    fun isArmstrong(number: Int): Boolean
}

class NarcissisticNumberImpl : NarcissisticNumber {

    /**
     * Function to check whether the number is armstrong number or not.
     * @param number Number
     * @return true if the number is armstrong.
     * @return false if the number is not armstrong.
     */
    override fun isArmstrong(number: Int): Boolean {
        if (number < 0) {
            return false
        }
        var sum = 0
        var temp = number
        val totalDigits = numberOfDigits(number)
        while (temp > 0) {
            val rem = temp % DECIMAL
            sum += rem.toDouble().pow(totalDigits.toDouble()).toInt()
            temp /= DECIMAL
        }
        return number == sum
    }

    private fun numberOfDigits(num: Int): Int {
        var n = num
        var totalDigits = 0
        while (n > 0) {
            n /= DECIMAL
            totalDigits++
        }
        return totalDigits
    }
}
