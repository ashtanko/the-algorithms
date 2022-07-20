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
package dev.shtanko.algorithms.search

/**
 * Binary search is an algorithm which finds the position of a target value within an array (Sorted)
 *
 * Worst-case performance       O(log(n))
 * Best-case performance        O(1)
 * Average performance          O(log(n))
 * Worst-case space complexity  O(1)
 */
class BinaryRecursiveSearch<T : Comparable<T>> : AbstractSearchStrategy<T> {
    override fun perform(arr: Array<T>, element: T): Int {
        return arr.search(0, arr.size - 1, element)
    }

    private fun Array<T>.search(low: Int, high: Int, element: T): Int {
        if (high >= low) {
            val mid = low.plus(high).div(2)

            if (this[mid] == element) {
                return mid
            }

            if (this[mid] > element) {
                return this.search(low, mid - 1, element)
            }
            return search(mid + 1, high, element)
        }
        return -1
    }
}
