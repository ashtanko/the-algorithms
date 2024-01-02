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

/**
 * 1. Two Sum
 * @link https://leetcode.com/problems/two-sum/
 */
fun interface TwoSumStrategy {
    /**
     * Performs the two-sum operation on the given array of integers.
     *
     * @param nums the array of integers.
     * @param target the target sum.
     * @return an array of indices representing the two numbers that add up to the target sum.
     */
    operator fun invoke(
        nums: IntArray,
        target: Int,
    ): IntArray
}

/**
 * Approach 1: Brute Force
 * Time complexity: O(n^2).
 * Space complexity: O(1).
 */
val twoSumBruteForce = TwoSumStrategy { nums: IntArray, target: Int ->
    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            if (nums[j] == target - nums[i]) {
                return@TwoSumStrategy intArrayOf(i, j)
            }
        }
    }
    return@TwoSumStrategy intArrayOf()
}

/**
 * Approach 2: Two-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
val twoSumTwoPassHashTable = TwoSumStrategy { nums: IntArray, target: Int ->
    val map: MutableMap<Int, Int> = HashMap()
    for (i in nums.indices) {
        map[nums[i]] = i
    }
    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement) && map[complement] != i) {
            return@TwoSumStrategy intArrayOf(i, map.getOrDefault(complement, 0))
        }
    }
    return@TwoSumStrategy intArrayOf()
}

/**
 * Approach 3: One-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
val twoSumOnePassHashTable = TwoSumStrategy { nums: IntArray, target: Int ->
    val map: MutableMap<Int, Int> = HashMap()
    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement)) {
            return@TwoSumStrategy intArrayOf(map.getOrDefault(complement, 0), i)
        }
        map[nums[i]] = i
    }
    return@TwoSumStrategy intArrayOf()
}

/**
 * Approach 4: Kotlin style One-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
val twoSumOneHashMap = TwoSumStrategy { nums: IntArray, target: Int ->
    val map: MutableMap<Int, Int> = HashMap()
    nums.forEachIndexed { index, i ->
        map[i]?.let { return@TwoSumStrategy intArrayOf(it, index) }
        map[target - i] = index
    }
    return@TwoSumStrategy intArrayOf()
}
