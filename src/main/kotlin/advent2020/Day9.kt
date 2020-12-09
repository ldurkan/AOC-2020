package advent2020

import kotlin.math.abs

class Day9(input: List<String>) {
    private val numbers = input.map { it.toLong() }

    fun solvePart1(preambleSize: Int = 25): Long = findFirstNumDoesNotSum(preambleSize).numThatDoesNotMatch

    fun solvePart2(preambleSize: Int = 25): Long = findContiguousRangeSumForNum(findFirstNumDoesNotSum(preambleSize))

    private fun findFirstNumDoesNotSum(preambleSize: Int): Result {
        val numToCheck = numbers.take(preambleSize).toMutableList()
        val data = numbers.drop(preambleSize)
        val runningTotals = linkedMapOf<Long, List<Long>>()
        var currentTotal = 0L
        numToCheck.forEach {
            if (currentTotal == 0L) runningTotals[it] = listOf(it)
            else runningTotals[currentTotal + it] = runningTotals[currentTotal]!! + it
            currentTotal += it
        }

        val notMatchNum = data.asSequence()
            .map { currentNum ->
                numToCheck.toList()
                    .asSequence()
                    .filter { numToCheck.contains(abs(currentNum - it)) }
                    .map {
                        numToCheck.add(currentNum)
                        numToCheck.removeFirst()
                        runningTotals[currentTotal + currentNum] = runningTotals[currentTotal]!! + currentNum
                        currentTotal += currentNum
                    }
                    .firstOrNull()
                    .let { Pair(currentNum, it != null) }
            }
            .first { !it.second }
            .first

        return Result(notMatchNum, runningTotals)
    }

    private fun findContiguousRangeSumForNum(result: Result): Long {
        val (numToMatch, runningTotals) = result
        return runningTotals.asSequence()
            .filter { it.key >= numToMatch }
            .first { runningTotals.containsKey(it.key - numToMatch) }
            .let { rangeEnd ->
                val rangeStart = runningTotals[rangeEnd.key - numToMatch]
                    ?: error("Could not find entry after filter")
                val contiguousRange = rangeEnd.value.drop(rangeStart.count())

                contiguousRange.minOrNull()!! + contiguousRange.maxOrNull()!!
            }
    }

    private data class Result(val numThatDoesNotMatch: Long, val runningTotals: Map<Long, List<Long>>)
}