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

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import kotlin.random.Random

@Suppress("ArrayPrimitive")
internal abstract class AbstractSortTest<out T : AbstractSortStrategy>(private val strategy: T) {

    internal class InputArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<Int>(), emptyArray<Int>()),
            Arguments.of(arrayOf(4), arrayOf(4)),
            Arguments.of(arrayOf(-4), arrayOf(-4)),
            Arguments.of(arrayOf(4, 8), arrayOf(4, 8)),
            Arguments.of(arrayOf(-4, -8), arrayOf(-8, -4)),
            Arguments.of(arrayOf(4, 4), arrayOf(4, 4)),
            Arguments.of(arrayOf(42, 23), arrayOf(23, 42)),
            Arguments.of(arrayOf(1, 2, -3), arrayOf(-3, 1, 2)),
            Arguments.of(arrayOf(42, 23, 16, 15, 8, 4), arrayOf(4, 8, 15, 16, 23, 42)),
            Arguments.of(arrayOf(15, 8, 16, 4, 42, 23), arrayOf(4, 8, 15, 16, 23, 42)),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), arrayOf(4, 8, 15, 16, 23, 42)),
            Arguments.of(arrayOf(-4, -8, -15, -16, -23, -42), arrayOf(-42, -23, -16, -15, -8, -4)),
            Arguments.of(arrayOf(-42, -23, -16, -15, -8, -4), arrayOf(-42, -23, -16, -15, -8, -4)),
            Arguments.of(arrayOf(4, 8, 15, 16, 42, 23), arrayOf(4, 8, 15, 16, 23, 42)),
            Arguments.of(arrayOf(45, 23, 53, 43, 18, 24, 8, 95, 101), arrayOf(8, 18, 23, 24, 43, 45, 53, 95, 101)),
            Arguments.of(arrayOf(41, 15, 82, 5, 65, 19, 32, 43, 8), arrayOf(5, 8, 15, 19, 32, 41, 43, 65, 82)),
            Arguments.of(arrayOf(22, 7, 2, -5, 8, 4), arrayOf(-5, 2, 4, 7, 8, 22)),
            Arguments.of(
                arrayOf(61, 109, 149, 111, 34, 2, 24, 119, 122, 125, 27, 145),
                arrayOf(2, 24, 27, 34, 61, 109, 111, 119, 122, 125, 145, 149)
            ),
        )
    }

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(getRandomArray(), true),
            Arguments.of(arrayOf(1, 2, 2, 1), true)
        )

        private fun getRandomArray(): Array<Int> {
            val arr = Array(10_000) { 0 }
            for (i in 0 until 10_000) {
                arr[i] = Random.nextInt(10_000)
            }
            return arr
        }
    }

    internal class InputStringArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<String>(), emptyArray<String>()),
            Arguments.of(arrayOf("A"), arrayOf("A")),
            Arguments.of(arrayOf("A", "B", "C"), arrayOf("A", "B", "C")),
            Arguments.of(arrayOf("D", "C", "B", "A"), arrayOf("A", "B", "C", "D")),
            Arguments.of(
                arrayOf("A", "c", "B", "e", "d", "F", "y", "G"),
                arrayOf("A", "B", "F", "G", "c", "d", "e", "y")
            )
        )
    }

    internal class InputObjectArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf(TestObject.empty()), arrayOf(TestObject.empty())),
            Arguments.of(
                arrayOf(TestObject(0, "Jake"), TestObject(3, "William")),
                arrayOf(TestObject(0, "Jake"), TestObject(3, "William"))
            ),
            Arguments.of(
                arrayOf(TestObject(0, "William"), TestObject(3, "Anna")),
                arrayOf(TestObject(3, "Anna"), TestObject(0, "William")),
            ),
            Arguments.of(
                arrayOf(TestObject(0, "Jake"), TestObject(1, "Anna"), TestObject(2, "Alex")),
                arrayOf(TestObject(2, "Alex"), TestObject(1, "Anna"), TestObject(0, "Jake")),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArrayArgumentsProvider::class)
    internal fun `integer array test`(arr: Array<Int>, expected: Array<Int>) {
        strategy.perform(arr)
        assertArrayEquals(expected, arr)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is sorted test`(arr: Array<Int>, expected: Boolean) {
        strategy.perform(arr)
        val actual = arr.isSorted()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputStringArrayArgumentsProvider::class)
    internal fun `string array test`(arr: Array<String>, expected: Array<String>) {
        strategy.perform(arr)
        assertArrayEquals(expected, arr)
    }

    @ParameterizedTest
    @ArgumentsSource(InputObjectArrayArgumentsProvider::class)
    internal fun `object test`(arr: Array<TestObject>, expected: Array<TestObject>) {
        strategy.perform(arr)
        assertArrayEquals(expected, arr)
    }
}
