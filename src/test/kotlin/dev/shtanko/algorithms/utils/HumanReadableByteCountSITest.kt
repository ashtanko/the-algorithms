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
package dev.shtanko.algorithms.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class HumanReadableByteCountSITest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                "0 B",
            ),
            Arguments.of(
                27,
                "27 B",
            ),
            Arguments.of(
                999,
                "999 B",
            ),
            Arguments.of(
                1000,
                "1.0 kB",
            ),
            Arguments.of(
                1023,
                "1.0 kB",
            ),
            Arguments.of(
                1024,
                "1.0 kB",
            ),
            Arguments.of(
                1728,
                "1.7 kB",
            ),
            Arguments.of(
                110592,
                "110.6 kB",
            ),
            Arguments.of(
                7077888,
                "7.1 MB",
            ),
            Arguments.of(
                452984832,
                "453.0 MB",
            ),
            Arguments.of(
                28991029248,
                "29.0 GB",
            ),
            Arguments.of(
                1855425871872,
                "1.9 TB",
            ),
            Arguments.of(
                9223372036854775807,
                "9.2 EB",
            ),
            Arguments.of(
                Long.MAX_VALUE,
                "9.2 EB",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `human readable byte count binary SI test`(bytes: Long, expected: String) {
        val actual = bytes.toHumanReadableByteCountSI()
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}