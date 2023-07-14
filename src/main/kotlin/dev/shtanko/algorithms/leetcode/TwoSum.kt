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
    fun perform(nums: IntArray, target: Int): IntArray
}

/**
 * Approach 1: Brute Force
 * Time complexity: O(n^2).
 * Space complexity: O(1).
 */
class TwoSumBruteForce : TwoSumStrategy {
    override fun perform(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[j] == target - nums[i]) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
        // or throw IllegalArgumentException("No two sum solution")
    }
}

/**
 * Approach 2: Two-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
class TwoSumTwoPassHashTable : TwoSumStrategy {
    override fun perform(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            map[nums[i]] = i
        }
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement) && map[complement] != i) {
                return intArrayOf(i, map[complement]!!)
            }
        }
        return intArrayOf()
        // or throw IllegalArgumentException("No two sum solution")
    }
}

/**
 * Approach 3: One-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
class TwoSumOnePassHashTable : TwoSumStrategy {
    override fun perform(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }
}

/**
 * Approach 4: Kotlin style One-pass Hash Table
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
class TwoSumOneHashMap : TwoSumStrategy {
    override fun perform(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        nums.forEachIndexed { index, i ->
            map[i]?.let { return intArrayOf(it, index) }
            map[target - i] = index
        }
        return intArrayOf()
    }
}
