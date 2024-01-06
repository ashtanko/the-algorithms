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
 * Quick sort is a highly efficient sorting algorithm and is based on the divide-and-conquer strategy.
 * It works by selecting a pivot element from the array and partitioning the other elements into two sub-arrays,
 * according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively.
 *
 * Developed by Tony Hoare in 1959, with his work published in 1961, Quicksort is an efficient sort algorithm using
 * divide and conquer approach. Quicksort first divides a large array into two smaller sub-arrays: the low elements
 * and the high elements. Quicksort can then recursively sort the sub-arrays. The steps are:
 * 1) Pick an element, called a pivot, from the array.
 * 2) Partitioning: reorder the array so that all elements with values less than the pivot come before the pivot,
 * while all elements with values greater than the pivot come after it (equal values can go either way).
 * After this partitioning, the pivot is in its final position. This is called the partition operation.
 * 3) Recursively apply the above steps to the sub-array of elements with smaller values and separately to
 * the sub-array of elements with greater values.
 *
 * Worst-case performance:       O(n^2)
 * Best-case performance:        O(n log n)
 * Average performance:          O(n log n)
 * Worst-case space complexity:  O(log n)
 */
data object QuickSort : AbstractSortStrategy {
    /**
     * Performs the quick sort operation on the given array.
     *
     * @param arr The array to sort.
     * @param T The type of elements in the array, must be comparable.
     */
    override fun <T : Comparable<T>> invoke(arr: Array<T>) {
        sort(arr, 0, arr.size - 1)
    }

    /**
     * Recursively sorts the array using quick sort.
     *
     * @param arr The array to sort.
     * @param low The low index of the current range.
     * @param high The high index of the current range.
     * @param T The type of elements in the array, must be comparable.
     */
    private fun <T : Comparable<T>> sort(
        arr: Array<T>,
        low: Int,
        high: Int,
    ) {
        if (arr.isEmpty()) {
            return
        }

        val divideIndex = partition(arr, low, high)

        if (low < divideIndex - 1) {
            // Sorting left half
            sort(arr, low, divideIndex - 1)
        }
        if (divideIndex < high) {
            // Sorting right half
            sort(arr, divideIndex, high)
        }
    }

    /**
     * Partitions the array into two sub-arrays using a pivot element.
     *
     * @param array The array to partition.
     * @param low The low index of the partition range.
     * @param high The high index of the partition range.
     * @return The index of the pivot element after partitioning.
     * @param T The type of elements in the array, must be comparable.
     */
    private fun <T : Comparable<T>> partition(
        array: Array<T>,
        low: Int,
        high: Int,
    ): Int {
        var left = low
        var right = high
        val pivot = array[(left + right) / 2]  // Pivot point
        while (left <= right) {
            while (array[left] < pivot) {
                left++
            }  // Find the elements on the left that should be on the right

            while (array[right] > pivot) {
                right--
            }  // Find the elements on the right that should be on the left

            // Swap elements, and move left and right indices
            if (left <= right) {
                array.swap(left, right)
                left++
                right--
            }
        }
        return left
    }
}
