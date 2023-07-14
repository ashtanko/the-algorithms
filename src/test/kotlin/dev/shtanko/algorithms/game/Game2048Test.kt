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
package dev.shtanko.algorithms.game

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource

class Game2048Test {

    class ShiftCellsDownInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(4, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 4, 0),
                    intArrayOf(0, 0, 4, 0),
                ),
            ),
        )
    }

    class ShiftCellsUpInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 4, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                ),
                arrayOf(
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                ),
                arrayOf(
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
        )
    }

    class ShiftCellsRightInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(0, 0, 4, 4),
                    intArrayOf(0, 0, 8, 8),
                    intArrayOf(0, 0, 16, 16),
                    intArrayOf(0, 0, 32, 32),
                ),
            ),
        )
    }

    class ShiftCellsLeftInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(4, 4, 0, 0),
                    intArrayOf(8, 8, 0, 0),
                    intArrayOf(16, 16, 0, 0),
                    intArrayOf(32, 32, 0, 0),
                ),
            ),
        )
    }

    class ManipulateGridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "a",
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(4, 4, 0, 0),
                    intArrayOf(8, 8, 0, 0),
                    intArrayOf(16, 16, 0, 0),
                    intArrayOf(32, 32, 0, 0),
                ),
            ),
            Arguments.of(
                "s",
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(4, 8, 16, 32),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(8, 16, 32, 64),
                ),
            ),
            Arguments.of(
                "d",
                arrayOf(
                    intArrayOf(4, 4, 2, 2),
                    intArrayOf(8, 8, 4, 4),
                    intArrayOf(16, 16, 8, 8),
                    intArrayOf(32, 32, 16, 16),
                ),
                arrayOf(
                    intArrayOf(0, 0, 8, 4),
                    intArrayOf(0, 0, 16, 8),
                    intArrayOf(0, 0, 32, 16),
                    intArrayOf(0, 0, 64, 32),
                ),
            ),
            Arguments.of(
                "w",
                arrayOf(
                    intArrayOf(2, 8, 32, 256),
                    intArrayOf(2, 8, 32, 256),
                    intArrayOf(4, 16, 128, 512),
                    intArrayOf(4, 16, 128, 512),
                ),
                arrayOf(
                    intArrayOf(4, 16, 64, 512),
                    intArrayOf(8, 32, 256, 1024),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
        )
    }

    class UpdateGridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                0 to 0,
                2,
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                0 to 1,
                2,
                arrayOf(
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                1 to 0,
                2,
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                3 to 3,
                2,
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 2),
                ),
            ),
        )
    }

    class GridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                ),
                true,
            ),
        )
    }

    class GridSolvedInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(512, 1024, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(512, 1024, 2048, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                true,
            ),
        )
    }

    @Test
    fun `display empty grid test`() {
        val grid = arrayOf(
            intArrayOf(),
        )
        display(grid)
    }

    @Test
    fun `display low grid test`() {
        val grid = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        display(grid)
    }

    @Test
    fun `organize test`() {
        val row = intArrayOf()
        val idxToMatch = 0
        val idxToCompare = 1
        val actual = organize(row, idxToMatch, idxToCompare)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `merge test`() {
        val row = intArrayOf()
        val idxToMatch = 0
        val idxToCompare = 1
        val actual = merge(row, idxToMatch, idxToCompare)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `merge and organize cells test`() {
        val row = intArrayOf()
        val actual = mergeAndOrganizeCells(row)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `shift cells down error test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            shiftCellsDown(grid)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsDownInputParams::class)
    fun `shift cells down test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsDown(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `shift cells up error test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            shiftCellsUp(grid)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsUpInputParams::class)
    fun `shift cells up test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsUp(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `shift cells right empty test`() {
        val grid = arrayOf<IntArray>()
        val actual = shiftCellsRight(grid)
        val expected = arrayOf<IntArray>()
        assertArrayEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsRightInputParams::class)
    fun `shift cells right test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsRight(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsLeftInputParams::class)
    fun `shift cells left test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsLeft(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `manipulate grid with unsupported input test`() {
        assertThrows(IllegalArgumentException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = ""
            manipulateGrid(grid, input)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ManipulateGridInputParams::class)
    fun `manipulate grid test`(input: String, grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = manipulateGrid(grid, input)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `manipulate grid with S input test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = "s"
            manipulateGrid(grid, input)
        }
    }

    @Test
    fun `manipulate grid with D input test`() {
        val grid = arrayOf(
            intArrayOf(),
        )
        val input = "d"
        val actual = manipulateGrid(grid, input)
        val expected = arrayOf(
            intArrayOf(),
        )
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `manipulate grid with W input test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = "w"
            manipulateGrid(grid, input)
        }
    }

    @Test
    fun `when invalid input test`() {
        assertFalse(isValidInput(""))
    }

    @DisplayName("Valid inputs")
    @ParameterizedTest
    @ValueSource(strings = ["a", "s", "d", "w"])
    fun `when valid input test`(inputString: String) {
        val actual = isValidInput(inputString)
        assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @ArgumentsSource(UpdateGridInputParams::class)
    fun `update grid test`(
        grid: Array<IntArray>,
        coordinates: Pair<Int, Int>,
        value: Int,
        expected: Array<IntArray>,
    ) {
        val actual = updateGrid(grid, coordinates, value)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun `generate number test`() {
        val actual = generateNumber()
        assertThat(actual).isLessThan(5)
    }

    @Test
    fun `locate spawn coordinates error test`() {
        val grid = arrayOf(
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        assertThrows<NoSuchElementException> {
            locateSpawnCoordinates(grid)
        }
    }

    @Test
    fun `locate spawn coordinates test`() {
        val grid = arrayOf(
            intArrayOf(0, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        val actual = locateSpawnCoordinates(grid)
        assertThat(actual).isEqualTo(0 to 0)
    }

    @Test
    fun `spawn number error test`() {
        val grid = arrayOf(
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        assertThrows<NoSuchElementException> {
            spawnNumber(grid)
        }
    }

    @Test
    fun `spawn number test`() {
        val grid = arrayOf(
            intArrayOf(0, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        val actual = spawnNumber(grid).first().first()
        assertThat(actual).isGreaterThan(0)
    }

    @ParameterizedTest
    @ArgumentsSource(GridInputParams::class)
    fun `is grid full test`(grid: Array<IntArray>, expected: Boolean) {
        val actual = isGridFull(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(GridSolvedInputParams::class)
    fun `is grid solved test`(grid: Array<IntArray>, expected: Boolean) {
        val actual = isGridSolved(grid)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `run solved grid test`() {
        val grid = arrayOf(
            intArrayOf(2, 4, 8, 16),
            intArrayOf(32, 64, 128, 256),
            intArrayOf(512, 1024, 2048, 2),
            intArrayOf(0, 0, 0, 0),
        )
        val actual = run2048(grid)
        val expected = "Congratulations! You won the game."
        assertThat(actual).isEqualTo(GameMessage(message = expected))
    }

    @Test
    fun `run full grid test`() {
        val grid = arrayOf(
            intArrayOf(2, 4, 8, 16),
            intArrayOf(32, 64, 128, 256),
            intArrayOf(512, 1024, 8, 2),
            intArrayOf(16, 32, 512, 256),
        )
        val actual = run2048(grid)
        val expected = GameMessage(message = "So sorry, but you lost the game.")
        assertThat(actual).isEqualTo(expected)
    }
}
