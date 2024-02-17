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

import dev.shtanko.algorithms.extensions.generateRandomArray
import dev.shtanko.algorithms.utils.measureTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.Locale
import java.util.stream.Stream
import kotlin.system.measureTimeMillis

class PerformanceTest {
    @ParameterizedTest
    @ArgumentsSource(SlowSortsArgumentsProvider::class)
    fun `slow sorts test`(strategy: AbstractSortStrategy, arr: IntArray) {
        executionTimeReport(strategy, arr)
    }

    @ParameterizedTest
    @ArgumentsSource(FastSortsArgumentsProvider::class)
    fun `fast sorts test`(strategy: AbstractSortStrategy, arr: IntArray) {
        executionTimeReport(strategy, arr)
    }

    @ParameterizedTest
    @ArgumentsSource(SlowScopeStrategiesInputArgumentsProvider::class)
    fun `slow strategies performance test`(n: Int, expected: Boolean) {
        val arr = n.generateRandomArray().toTypedArray()
        val fastStrategies = listOf(
            BubbleSort,
            SimpleBubbleSort,
            JvmSort,
            PancakeSort,
            GnomeSort(),
            InsertionSort,
            InsertionSort2,
            SelectionSort,
            StableSelectionSort,
        )
        strategiesPerformanceTest(arr, fastStrategies, expected)
    }

    @ParameterizedTest
    @ArgumentsSource(FastScopeStrategiesInputArgumentsProvider::class)
    fun `fast strategies performance test`(n: Int, expected: Boolean) {
        val arr = n.generateRandomArray().toTypedArray()
        val fastStrategies = listOf(
            MergeSort,
            QuickSort,
            ShellSort(),
            ShellSort(),
        )
        strategiesPerformanceTest(arr, fastStrategies, expected)
    }

    private fun strategiesPerformanceTest(
        arr: Array<Int>,
        strategies: List<AbstractSortStrategy>,
        expected: Boolean,
    ) {
        val totalTime = measureTimeMillis {
            strategies.map {
                it.invoke(arr)
            }
        }
        println(
            String.format(
                Locale.getDefault(),
                "Given arrays of length %d %s Consumed time: %d ms",
                arr.size,
                "Fast strategies",
                totalTime,
            ),
        )
        assertThat(arr.isSorted()).isEqualTo(expected)
    }

    private fun executionTimeReport(strategy: AbstractSortStrategy, array: IntArray) {
        val arr = array.toTypedArray()
        measureTime(strategy, array) {
            strategy(arr)
        }
        assertTrue(arr.isSorted())
    }

    class SlowSortsArgumentsProvider : ArgumentsProvider {
        private val hundred = 100.generateRandomArray()
        private val fiveHundred = 500.generateRandomArray()
        private val eightHundred = 800.generateRandomArray()
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(BubbleSort, hundred),
            Arguments.of(BubbleSort, fiveHundred),
            Arguments.of(BubbleSort, eightHundred),
            Arguments.of(SimpleBubbleSort, hundred),
            Arguments.of(SimpleBubbleSort, fiveHundred),
            Arguments.of(SimpleBubbleSort, eightHundred),
            Arguments.of(JvmSort, hundred),
            Arguments.of(JvmSort, fiveHundred),
            Arguments.of(JvmSort, eightHundred),
            Arguments.of(PancakeSort, hundred),
            Arguments.of(PancakeSort, fiveHundred),
            Arguments.of(PancakeSort, eightHundred),
            Arguments.of(GnomeSort(), hundred),
            Arguments.of(GnomeSort(), fiveHundred),
            Arguments.of(GnomeSort(), eightHundred),
            Arguments.of(InsertionSort, hundred),
            Arguments.of(InsertionSort, fiveHundred),
            Arguments.of(InsertionSort, eightHundred),
            Arguments.of(InsertionSort2, hundred),
            Arguments.of(InsertionSort2, fiveHundred),
            Arguments.of(InsertionSort2, eightHundred),
            Arguments.of(SelectionSort, hundred),
            Arguments.of(SelectionSort, fiveHundred),
            Arguments.of(SelectionSort, eightHundred),
            Arguments.of(StableSelectionSort, hundred),
            Arguments.of(StableSelectionSort, fiveHundred),
            Arguments.of(StableSelectionSort, eightHundred),
        )
    }

    class FastSortsArgumentsProvider : ArgumentsProvider {
        private val mergeSortStrategy = MergeSort
        private val quickSortStrategy = QuickSort
        private val shellSortStrategy = ShellSort()
        private val heapSortStrategy = ShellSort()
        private val thirtyK = 30_000.generateRandomArray()
        private val fiftyK = 50_000.generateRandomArray()
        private val hundredK = 100_000.generateRandomArray()
        private val fiveHundredK = 500_000.generateRandomArray()

        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(mergeSortStrategy, thirtyK),
            Arguments.of(mergeSortStrategy, fiftyK),
            Arguments.of(mergeSortStrategy, hundredK),
            Arguments.of(mergeSortStrategy, fiveHundredK),
            Arguments.of(quickSortStrategy, thirtyK),
            Arguments.of(quickSortStrategy, fiftyK),
            Arguments.of(quickSortStrategy, hundredK),
            Arguments.of(quickSortStrategy, fiveHundredK),
            Arguments.of(shellSortStrategy, thirtyK),
            Arguments.of(shellSortStrategy, fiftyK),
            Arguments.of(shellSortStrategy, hundredK),
            Arguments.of(shellSortStrategy, fiveHundredK),
            Arguments.of(heapSortStrategy, thirtyK),
            Arguments.of(heapSortStrategy, fiftyK),
            Arguments.of(heapSortStrategy, hundredK),
            Arguments.of(heapSortStrategy, fiveHundredK),
        )
    }

    private class FastScopeStrategiesInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(10_000, true),
            Arguments.of(100_000, true),
            Arguments.of(500_000, true),
            Arguments.of(1000_000, true),
        )
    }

    private class SlowScopeStrategiesInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(5000, true),
            Arguments.of(1000, true),
            Arguments.of(10_000, true),
            Arguments.of(20_000, true),
        )
    }
}
