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

import kotlin.math.max
import kotlin.math.min

/**
 * 583. Delete Operation for Two Strings
 * @link https://leetcode.com/problems/delete-operation-for-two-strings/
 */
fun interface MinDistance {
    fun perform(word1: String, word2: String): Int
}

/**
 * Approach #1 Using Longest Common Subsequence
 * Time complexity : O(2^{max(m,n)}).
 * Space complexity : O(max (m,n))).
 */
class MinDistanceLCS : MinDistance {
    override fun perform(word1: String, word2: String): Int {
        return word1.length + word2.length - 2 * lcs(word1, word2, word1.length, word2.length)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0
        return if (s1[m - 1] == s2[n - 1]) {
            1 + lcs(s1, s2, m - 1, n - 1)
        } else {
            max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n))
        }
    }
}

/**
 * Approach #2 Longest Common Subsequence with Memoization
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceLCSMemo : MinDistance {
    override fun perform(word1: String, word2: String): Int {
        val memo = Array(word1.length + 1) {
            IntArray(
                word2.length + 1,
            )
        }
        return word1.length + word2.length - 2 * lcs(word1, word2, word1.length, word2.length, memo)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int, memo: Array<IntArray>): Int {
        if (m == 0 || n == 0) {
            return 0
        }
        if (memo[m][n] > 0) {
            return memo[m][n]
        }
        if (s1[m - 1] == s2[n - 1]) {
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo)
        } else {
            memo[m][n] = max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo))
        }
        return memo[m][n]
    }
}

/**
 * Approach #3 Using Longest Common Subsequence- Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceLCSDP : MinDistance {
    override fun perform(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
        for (i in 0..word1.length) {
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) continue
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return word1.length + word2.length - 2 * dp[word1.length][word2.length]
    }
}

/**
 * Approach #4 Without using LCS Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceDP : MinDistance {
    override fun perform(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) {
            IntArray(
                word2.length + 1,
            )
        }
        for (i in 0..word1.length) {
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j
                } else if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] =
                        dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[word1.length][word2.length]
    }
}

/**
 * Approach #5 1-D Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(n).
 */
class MinDistance1DDP : MinDistance {
    override fun perform(word1: String, word2: String): Int {
        var dp = IntArray(word2.length + 1)
        for (i in 0..word1.length) {
            val temp = IntArray(word2.length + 1)
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) {
                    temp[j] = i + j
                } else if (word1[i - 1] == word2[j - 1]) {
                    temp[j] = dp[j - 1]
                } else {
                    temp[j] = 1 + min(dp[j], temp[j - 1])
                }
            }
            dp = temp
        }
        return dp[word2.length]
    }
}
