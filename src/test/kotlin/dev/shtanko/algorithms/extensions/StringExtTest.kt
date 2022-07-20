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

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class StringExtTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("01", true),
            Arguments.of("A", false),
            Arguments.of("1A", false),
            Arguments.of("", false),
            Arguments.of(" ", false),
            Arguments.of(",", false),
            Arguments.of("a", false),
            Arguments.of("11111111111A", false),
            Arguments.of("111111111110000000", true)
        )
    }

    internal class InputPrefixArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                "",
                ""
            ),
            Arguments.of(
                "a",
                "a",
                "a"
            ),
            Arguments.of(
                "abc",
                "ab",
                "ab"
            ),
            Arguments.of(
                "qwertyuiop",
                "qweryuiop",
                "qwer"
            ),
        )
    }

    internal class CountZeroesArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                intArrayOf(0, 0)
            ),
            Arguments.of(
                "0",
                intArrayOf(1, 0)
            ),
            Arguments.of(
                "1",
                intArrayOf(0, 1)
            ),
            Arguments.of(
                "100",
                intArrayOf(2, 1)
            ),
            Arguments.of(
                "11",
                intArrayOf(0, 2)
            ),
            Arguments.of(
                "000000000000001",
                intArrayOf(14, 1)
            ),
            Arguments.of(
                "000000000000001111111111111111111111111111111111111111000",
                intArrayOf(17, 40)
            ),
        )
    }

    internal class IntArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "b",
                1
            ),
            Arguments.of(
                "a",
                0
            ),
            Arguments.of(
                "ba",
                10
            ),
            Arguments.of(
                "acb",
                21
            ),
            Arguments.of(
                "cba",
                210
            ),
            Arguments.of(
                "cdb",
                231
            ),
        )
    }

    internal class RemoveZeroesArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "0",
                "0"
            ),
            Arguments.of(
                "00",
                "0"
            ),
            Arguments.of(
                "01",
                "1"
            ),
            Arguments.of(
                "001",
                "1"
            ),
            Arguments.of(
                "",
                ""
            ),
            Arguments.of(
                "1",
                "1"
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(CountZeroesArgumentsProvider::class)
    internal fun `count zeroes ones test`(str: String, expected: IntArray) {
        val actual = str.countZeroesOnes()
        Assert.assertArrayEquals(actual, expected)
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is binary test`(str: String, expected: Boolean) {
        val actual = str.isBinary()
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputPrefixArgumentsProvider::class)
    internal fun `common prefix test`(left: String, right: String, expected: String) {
        val actual = (left to right).commonPrefix()
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(IntArgumentsProvider::class)
    internal fun `int or string test`(s: String, expected: Int) {
        val actual = s.getNumberOfLetter()
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(RemoveZeroesArgumentsProvider::class)
    internal fun `remove zeroes in begin test`(s: String, expected: String) {
        val actual = s.removeZeroesInBegin()
        assertThat(actual, equalTo(expected))
    }
}
