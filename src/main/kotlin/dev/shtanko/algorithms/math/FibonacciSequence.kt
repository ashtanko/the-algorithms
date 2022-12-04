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

fun Int.toFibonacciSequence(): Int {
    if (this <= 1) {
        return this
    }
    return (this - 1).toFibonacciSequence() + (this - 2).toFibonacciSequence()
}

fun fibonacciAt(n: Int) = run {
    tailrec fun fibonacciAcc(n: Int, a: Long, b: Long): Long {
        return when (n == 0) {
            true -> b
            false -> fibonacciAcc(n - 1, a + b, a)
        }
    }
    fibonacciAcc(n, 1, 0)
}

enum class Fibonacci {
    ITERATIVE {
        override fun invoke(n: Long) = if (n < 2) {
            n
        } else {
            var n1 = 0L
            var n2 = 1L
            var i = n
            do {
                val sum = n1 + n2
                n1 = n2
                n2 = sum
            } while (i-- > 1)
            n1
        }
    },
    RECURSIVE {
        override fun invoke(n: Long): Long = if (n < 2) n else this(n - 1) + this(n - 2)
    };

    abstract operator fun invoke(n: Long): Long
}
