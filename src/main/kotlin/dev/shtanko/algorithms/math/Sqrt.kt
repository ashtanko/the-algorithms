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

import kotlin.math.abs

private const val EPSILON = 1e-15

/**
 * Calculates the square root of the given integer.
 *
 * @param c The integer to calculate the square root of.
 * @param e The tolerance value for convergence (default: EPSILON).
 * @return The square root of the given integer.
 */
fun sqrt(
    c: Int,
    e: Double = EPSILON,
): Double = sqrt(c.toDouble(), e)

/**
 * Calculates the square root of the given double.
 *
 * @param c The double to calculate the square root of.
 * @param e The tolerance value for convergence (default: EPSILON).
 * @return The square root of the given double.
 */
fun sqrt(
    c: Double,
    e: Double = EPSILON,
): Double {
    if (c < 0) {
        return Double.NaN
    }
    var t = c
    while (abs(t - c / t) > e * t) {
        t = (c / t + t) / 2.0
    }
    return t
}
