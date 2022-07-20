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
package dev.shtanko.algorithms.extensions

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ArrayExtensionTest {

    @Test
    internal fun `simple swap test`() {
        val arr = arrayOf(4, 8)
        arr.swap(1, 0)
        assertArrayEquals(arrayOf(8, 4), arr)
    }

    @Test
    internal fun `loop swap test`() {
        val arr = arrayOf(4, 8, 15, 16)
        for (i in 0 until arr.size - 1) {
            arr.swap(i, i + 1)
        }
        assertArrayEquals(arrayOf(8, 15, 16, 4), arr)
    }

    @Test
    internal fun `simple reverse test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.reverse()
        assertArrayEquals(arrayOf(42, 23, 16, 15, 8, 4), arr)
    }

    @Test
    internal fun `two pointers technique reverse test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.reverse2()
        assertArrayEquals(arrayOf(42, 23, 16, 15, 8, 4), arr)
    }

    @Test
    internal fun `flip test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.flip(0, arr.size - 1)
        val expected = arrayOf(42, 23, 16, 15, 8, 4)
        assertArrayEquals(expected, arr)
    }

    @Test
    internal fun `char swap test`() {
        val arr = charArrayOf('A', 'B')
        arr.swap(1, 0)
        assertArrayEquals(charArrayOf('B', 'A'), arr)
    }

    @Test
    internal fun `int array swap test`() {
        val arr = intArrayOf(4, 8)
        arr.swap(1, 0)
        assertArrayEquals(intArrayOf(8, 4), arr)
    }

    @Test
    internal fun `second function on empty array test`() {
        val array = intArrayOf()
        assertThrows(NoSuchElementException::class.java) {
            array.second()
        }
    }

    @Test
    internal fun `second function on single item array test`() {
        val array = intArrayOf(1)
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            array.second()
        }
    }

    @Test
    internal fun `second function on array test`() {
        val array = intArrayOf(1, 2)
        assertEquals(2, array.second())
    }
}
