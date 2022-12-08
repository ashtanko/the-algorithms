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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class HumanReadableBytesTest {
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
                "1000 B",
            ),
            Arguments.of(
                1023,
                "1023 B",
            ),
            Arguments.of(
                1024,
                "1.0 KiB",
            ),
            Arguments.of(
                1728,
                "1.7 KiB",
            ),
            Arguments.of(
                110592,
                "108.0 KiB",
            ),
            Arguments.of(
                7077888,
                "6.8 MiB",
            ),
            Arguments.of(
                452984832,
                "432.0 MiB",
            ),
            Arguments.of(
                28991029248,
                "27.0 GiB",
            ),
            Arguments.of(
                1855425871872,
                "1.7 TiB",
            ),
            Arguments.of(
                9223372036854775807,
                "8.0 EiB",
            ),
            Arguments.of(
                Long.MAX_VALUE,
                "8.0 EiB",
            ),
            Arguments.of(
                -0,
                "0 B",
            ),
            Arguments.of(
                -1,
                "-1 B",
            ),
            Arguments.of(
                -27,
                "-27 B",
            ),
            Arguments.of(
                -999,
                "-999 B",
            ),
            Arguments.of(
                -1000,
                "-1000 B",
            ),
            Arguments.of(
                -1023,
                "-1023 B",
            ),
            Arguments.of(
                -1024,
                "-1.0 KiB",
            ),
            Arguments.of(
                -1728,
                "-1.7 KiB",
            ),
            Arguments.of(
                -110592,
                "-108.0 KiB",
            ),
            Arguments.of(
                -7077888,
                "-6.8 MiB",
            ),
            Arguments.of(
                -452984832,
                "-432.0 MiB",
            ),
            Arguments.of(
                -28991029248,
                "-27.0 GiB",
            ),
            Arguments.of(
                -1855425871872,
                "-1.7 TiB",
            ),
            Arguments.of(
                -9223372036854775807,
                "-8.0 EiB",
            ),
            Arguments.of(
                -Long.MAX_VALUE,
                "-8.0 EiB",
            ),
            Arguments.of(
                -Long.MIN_VALUE,
                "-8.0 EiB",
            ),
            Arguments.of(
                Long.MIN_VALUE,
                "-8.0 EiB",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `human readable byte count binary test`(bytes: Long, expected: String) {
        val actual = bytes.toHumanReadableByteCountBin()
        assertThat(actual).isEqualTo(expected)
    }
}
