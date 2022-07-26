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
package dev.shtanko.algorithms.sorts

import dev.shtanko.algorithms.extensions.swap

/**
 * Heap sort is a comparison based sorting technique based on Binary Heap data structure. It is similar to selection
 * sort where we first find the maximum element and place the maximum element at the end. We repeat the same process
 * for remaining element.
 *
 * Worst-case performance       O(n*log(n))
 * Best-case performance        O(n*log(n))
 * Average-case performance     O(n*log(n))
 * Worst-case space complexity  O(1) (auxiliary)
 */
class HeapSort : AbstractSortStrategy {

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        val n = arr.size
        for (i in n / 2 - 1 downTo 0) {
            heapify(arr, n, i)
        }

        for (i in n - 1 downTo 0) {
            arr.swap(0, i)
            heapify(arr, i, 0)
        }
    }

    private fun <T : Comparable<T>> heapify(arr: Array<T>, n: Int, i: Int) {
        var largest = i
        val l = 2 * i + 1
        val r = 2 * i + 2

        if (l < n && arr[l] > arr[largest]) {
            largest = l
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r
        }

        if (largest != i) {
            arr.swap(i, largest)
            heapify(arr, n, largest)
        }
    }
}
