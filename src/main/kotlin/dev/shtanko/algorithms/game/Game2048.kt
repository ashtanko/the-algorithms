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

import kotlin.random.Random

/**
 * Represents the game over message.
 */
@JvmInline
value class GameMessage(val message: String) {
    companion object {
        /**
         * Message displayed when the game is won.
         */
        val POSITIVE_GAME_OVER = GameMessage("Congratulations! You won the game.")

        /**
         * Message displayed when the game is lost.
         */
        val NEGATIVE_GAME_OVER = GameMessage("So sorry, but you lost the game.")
    }
}

private const val SOLVED = 2048
private const val RANDOM = 0.10

/**
 * Runs the 2048 game.
 */
fun run2048(grid: Array<IntArray>): GameMessage {
    if (isGridSolved(grid)) {
        return GameMessage.POSITIVE_GAME_OVER
    } else if (isGridFull(grid)) {
        return GameMessage.NEGATIVE_GAME_OVER
    }

    val populatedGrid = spawnNumber(grid)
    display(populatedGrid)

    return run2048(manipulateGrid(populatedGrid, waitForValidInput()))
}

/**
 * Checks if the grid contains the SOLVED value.
 */
fun isGridSolved(grid: Array<IntArray>): Boolean = grid.any { row -> SOLVED in row }

/**
 * Checks if the grid is full (no empty cells).
 */
fun isGridFull(grid: Array<IntArray>): Boolean = grid.all { row -> 0 !in row }

/**
 * Spawns a new number in the grid at an empty cell.
 */
fun spawnNumber(grid: Array<IntArray>): Array<IntArray> {
    val coordinates = locateSpawnCoordinates(grid)
    val number = generateNumber()

    return updateGrid(grid, coordinates, number)
}

/**
 * Locates the coordinates of an empty cell in the grid.
 */
fun locateSpawnCoordinates(grid: Array<IntArray>): Pair<Int, Int> {
    val emptyCells = arrayListOf<Pair<Int, Int>>()
    grid.forEachIndexed { x, row ->
        row.forEachIndexed { y, cell ->
            if (cell == 0) emptyCells.add(Pair(x, y))
        }
    }

    return emptyCells.random()
}

/**
 * Generates a random number (2 or 4) based on the RANDOM probability.
 */
fun generateNumber(): Int = if (Random.nextDouble() > RANDOM) 2 else 4

/**
 * Updates the grid by placing a new number at the specified coordinates.
 */
fun updateGrid(grid: Array<IntArray>, at: Pair<Int, Int>, value: Int): Array<IntArray> {
    val updatedGrid = grid.copyOf()
    updatedGrid[at.first][at.second] = value
    return updatedGrid
}

/**
 * Waits for valid user input (a, s, d, w).
 */
fun waitForValidInput(): String {
    val input = waitForInput()
    return if (isValidInput(input)) input else waitForValidInput()
}

/**
 * Checks if the user input is valid.
 */
fun isValidInput(input: String): Boolean = arrayOf("a", "s", "d", "w").contains(input)

/**
 * Waits for user input from the console.
 */
fun waitForInput(): String {
    println("Direction? (a - shift left, s - shift down, d - shift right, w - shift up)")
    return readlnOrNull().orEmpty()
}

/**
 * Manipulates the grid based on the user input.
 */
fun manipulateGrid(grid: Array<IntArray>, input: String): Array<IntArray> = when (input) {
    "a" -> shiftCellsLeft(grid)
    "s" -> shiftCellsDown(grid)
    "d" -> shiftCellsRight(grid)
    "w" -> shiftCellsUp(grid)
    else -> throw IllegalArgumentException("Expected one of [a, s, d, w]")
}

/**
 * Shifts the cells in the grid to the left.
 */
fun shiftCellsLeft(grid: Array<IntArray>): Array<IntArray> =
    grid.map(::mergeAndOrganizeCells).toTypedArray()

/**
 * Shifts the cells in the grid to the right.
 */
fun shiftCellsRight(grid: Array<IntArray>): Array<IntArray> =
    grid.map { row -> mergeAndOrganizeCells(row.reversed().toIntArray()).reversed().toIntArray() }.toTypedArray()

/**
 * Shifts the cells in the grid upwards.
 */
fun shiftCellsUp(grid: Array<IntArray>): Array<IntArray> {
    val transposedGrid = grid.transpose()
    val updatedGrid = transposedGrid.map(::mergeAndOrganizeCells).toTypedArray()
    return updatedGrid.transpose()
}

/**
 * Shifts the cells in the grid downwards.
 */
fun shiftCellsDown(grid: Array<IntArray>): Array<IntArray> {
    val transposedGrid = grid.transpose()
    val updatedGrid =
        transposedGrid.map { row -> mergeAndOrganizeCells(row.reversed().toIntArray()).reversed().toIntArray() }
            .toTypedArray()
    return updatedGrid.transpose()
}

/**
 * Merges and organizes the cells in a row.
 */
fun mergeAndOrganizeCells(row: IntArray): IntArray = organize(merge(row.copyOf()))

/**
 * Merges adjacent cells in a row.
 */
fun merge(row: IntArray, idxToMatch: Int = 0, idxToCompare: Int = 1): IntArray {
    if (idxToMatch >= row.size) return row
    if (idxToCompare >= row.size) return merge(row, idxToMatch + 1, idxToMatch + 2)
    if (row[idxToMatch] == 0) return merge(row, idxToMatch + 1, idxToMatch + 2)

    return if (row[idxToMatch] == row[idxToCompare]) {
        row[idxToMatch] *= 2
        row[idxToCompare] = 0
        merge(row, idxToMatch + 1, idxToMatch + 2)
    } else {
        if (row[idxToCompare] != 0) {
            merge(row, idxToMatch + 1, idxToMatch + 2)
        } else {
            merge(row, idxToMatch, idxToCompare + 1)
        }
    }
}

/**
 * Organizes the cells in a row by moving them towards the beginning.
 */
fun organize(row: IntArray, idxToMatch: Int = 0, idxToCompare: Int = 1): IntArray {
    if (idxToMatch >= row.size) return row
    if (idxToCompare >= row.size) return organize(row, idxToMatch + 1, idxToMatch + 2)
    if (row[idxToMatch] != 0) return organize(row, idxToMatch + 1, idxToMatch + 2)

    return if (row[idxToCompare] != 0) {
        row[idxToMatch] = row[idxToCompare]
        row[idxToCompare] = 0
        organize(row, idxToMatch + 1, idxToMatch + 2)
    } else {
        organize(row, idxToMatch, idxToCompare + 1)
    }
}

/**
 * Displays the grid in a user-friendly format.
 */
fun display(grid: Array<IntArray>) {
    val prettyPrintableGrid = grid.map { row ->
        row.joinToString("|") { cell ->
            if (cell == 0) "    " else "%4d".format(cell)
        }
    }

    println("New Grid:")
    prettyPrintableGrid.forEach { row ->
        println("+----+----+----+----+")
        println(row)
    }
    println("+----+----+----+----+")
}

/**
 * Transposes a grid (rows become columns, and columns become rows).
 */
fun Array<IntArray>.transpose(): Array<IntArray> =
    Array(size) { row -> IntArray(size) { col -> this[col][row] } }

/**
 * Entry point of the program.
 */
fun main() {
    val grid = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
    )

    val gameOverMessage = run2048(grid)
    println(gameOverMessage)
}
