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
    operator fun invoke(
        word1: String,
        word2: String,
    ): Int
}

/**
 * Approach #1 Using Longest Common Subsequence
 * Time complexity : O(2^{max(m,n)}).
 * Space complexity : O(max (m,n))).
 */
val minDistanceLcs = MinDistance { word1: String, word2: String ->

    fun longestCommonSubsequenceLength(
        word1: String,
        word2: String,
        length1: Int,
        length2: Int,
    ): Int {
        if (length1 == 0 || length2 == 0) {
            return 0
        }
        return if (word1[length1 - 1] == word2[length2 - 1]) {
            1 + longestCommonSubsequenceLength(word1, word2, length1 - 1, length2 - 1)
        } else {
            max(
                longestCommonSubsequenceLength(word1, word2, length1, length2 - 1),
                longestCommonSubsequenceLength(word1, word2, length1 - 1, length2),
            )
        }
    }

    return@MinDistance word1.length + word2.length - 2 * longestCommonSubsequenceLength(
        word1,
        word2,
        word1.length,
        word2.length,
    )
}

/**
 * Approach #2 Longest Common Subsequence with Memoization
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
val minDistanceLcsMemo = MinDistance { word1: String, word2: String ->
    fun longestCommonSubsequenceLength(
        word1: String,
        word2: String,
        length1: Int,
        length2: Int,
        memo: Array<IntArray>,
    ): Int {
        if (length1 == 0 || length2 == 0) {
            return 0
        }
        if (memo[length1][length2] > 0) {
            return memo[length1][length2]
        }
        if (word1[length1 - 1] == word2[length2 - 1]) {
            memo[length1][length2] = 1 + longestCommonSubsequenceLength(word1, word2, length1 - 1, length2 - 1, memo)
        } else {
            memo[length1][length2] = max(
                longestCommonSubsequenceLength(word1, word2, length1, length2 - 1, memo),
                longestCommonSubsequenceLength(word1, word2, length1 - 1, length2, memo),
            )
        }
        return memo[length1][length2]
    }

    val memo =
        Array(word1.length + 1) {
            IntArray(
                word2.length + 1,
            )
        }
    return@MinDistance word1.length + word2.length - 2 * longestCommonSubsequenceLength(
        word1,
        word2,
        word1.length,
        word2.length,
        memo,
    )
}

/**
 * Approach #3 Using Longest Common Subsequence- Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
val minDistanceLcsDp = MinDistance { word1: String, word2: String ->
    val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
    for (i in 0..word1.length) {
        for (j in 0..word2.length) {
            if (i == 0 || j == 0) {
                continue
            }
            if (word1[i - 1] == word2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }
    word1.length + word2.length - 2 * dp[word1.length][word2.length]
}

/**
 * Approach #4 Without using LCS Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
val minDistanceDp = MinDistance { word1: String, word2: String ->
    val dp =
        Array(word1.length + 1) {
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
    dp[word1.length][word2.length]
}

/**
 * Approach #5 1-D Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(n).
 */
val minDistance1Ddp = MinDistance { word1: String, word2: String ->
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
    dp[word2.length]
}
