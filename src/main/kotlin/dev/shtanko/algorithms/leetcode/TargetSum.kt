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

import kotlin.math.abs

/**
 * 494. Target Sum
 * @link https://leetcode.com/problems/target-sum/
 */
fun interface TargetSum {
    fun findTargetSumWays(nums: IntArray, target: Int): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity: O(2^n)
 * Space complexity: O(n)
 */
class TargetSumBruteForce : TargetSum {
    private var count = 0

    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        calculate(nums, 0, 0, target)
        return count
    }

    private fun calculate(nums: IntArray, i: Int, sum: Int, target: Int) {
        if (i == nums.size) {
            if (sum == target) {
                count++
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], target)
            calculate(nums, i + 1, sum - nums[i], target)
        }
    }
}

/**
 * Approach 2: Recursion with Memoization
 * Time complexity: O(t⋅n)
 * Space complexity: O(t⋅n)
 */
class TargetSumMemoization : TargetSum {
    private var total = 0

    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        total = nums.sum()

        val memo = Array(nums.size) { IntArray(2 * total + 1) { Int.MIN_VALUE } }
        return calculate(nums, 0, 0, target, memo)
    }

    private fun calculate(nums: IntArray, i: Int, sum: Int, target: Int, memo: Array<IntArray>): Int {
        return if (i == nums.size) {
            if (sum == target) {
                1
            } else {
                0
            }
        } else {
            if (memo[i][sum + total] != Int.MIN_VALUE) {
                return memo[i][sum + total]
            }
            val add = calculate(nums, i + 1, sum + nums[i], target, memo)
            val subtract = calculate(nums, i + 1, sum - nums[i], target, memo)
            memo[i][sum + total] = add + subtract
            memo[i][sum + total]
        }
    }
}

/**
 * Approach 3: 2D Dynamic Programming
 * Time complexity: O(t⋅n)
 * Space complexity: O(t⋅n)
 */
class TwoDDynamicProgramming : TargetSum {
    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val total: Int = nums.sum()
        val dp = Array(nums.size) { IntArray(2 * total + 1) }
        dp[0][nums[0] + total] = 1
        dp[0][-nums[0] + total] += 1

        for (i in 1 until nums.size) {
            for (sum in -total..total) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total]
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total]
                }
            }
        }
        return if (abs(target) > total) 0 else dp[nums.size - 1][target + total]
    }
}

/**
 * Approach 4: 1D Dynamic Programming
 * Time complexity: O(t⋅n)
 * Space complexity: O(t)
 */
class OneDDynamicProgramming : TargetSum {
    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val total: Int = nums.sum()
        var dp = IntArray(2 * total + 1)
        dp[nums[0] + total] = 1
        dp[-nums[0] + total] += 1

        for (i in 1 until nums.size) {
            val next = IntArray(2 * total + 1)
            for (sum in -total..total) {
                if (dp[sum + total] > 0) {
                    next[sum + nums[i] + total] += dp[sum + total]
                    next[sum - nums[i] + total] += dp[sum + total]
                }
            }
            dp = next
        }

        return if (abs(target) > total) 0 else dp[target + total]
    }
}
