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

internal class SelectionSortTest : AbstractSortTest<SelectionSort>(SelectionSort())

internal class StableSelectionSortTest : AbstractSortTest<StableSelectionSort>(StableSelectionSort())

internal class ShellSortTest : AbstractSortTest<ShellSort>(ShellSort())

internal class QuickSortTest : AbstractSortTest<QuickSort>(QuickSort())

internal class MergeSortTest : AbstractSortTest<MergeSort>(MergeSort())

internal class InsertionSortTest : AbstractSortTest<InsertionSort>(InsertionSort())

internal class InsertionSort2Test : AbstractSortTest<InsertionSort2>(InsertionSort2())

internal class HeapSortTest : AbstractSortTest<HeapSort>(HeapSort())

internal class BubbleSortTest : AbstractSortTest<BubbleSort>(BubbleSort())

internal class SimpleBubbleSortTest : AbstractSortTest<SimpleBubbleSort>(SimpleBubbleSort())

internal class ArraySortTest : AbstractSortTest<ArraySort>(ArraySort())

internal class PancakeSortTest : AbstractSortTest<PancakeSort>(PancakeSort())

internal class GnomeSortTest : AbstractSortTest<GnomeSort>(GnomeSort())

internal class QuickSortRecursiveTest2 : AbstractSortTest<QuickSortRecursive>(QuickSortRecursive())
