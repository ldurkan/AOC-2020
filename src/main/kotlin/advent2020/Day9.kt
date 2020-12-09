package advent2020

import kotlin.math.abs

fun main() {
    println(Day9(Resources.resourceAsList("day9.input")).solvePart2())
}

class Day9(input : List<String>) {
    private val numbers = input.map { it.toLong() }

    fun solvePart1(preambleSize : Int = 25) : Long = findFirstNumDoesNotSum(preambleSize).first

    fun solvePart2(preambleSize: Int = 25) : Long = findFirstNumDoesNotSum(preambleSize).second

    private fun findFirstNumDoesNotSum(preambleSize: Int) : Pair<Long, Long> {
        val numToCheck = numbers.take(preambleSize).toMutableList()
        val data = numbers.drop(preambleSize)
        val runningTotals = mutableListOf<Pair<Long, Long>>()
        var currentTotal = 0L
        numToCheck.forEach {
            if (currentTotal == 0L) runningTotals.add(Pair(it, it))
            else runningTotals.add(Pair(it, currentTotal + it))
            currentTotal += it
        }

         val notMatchNum = data.asSequence()
            .map { currentNum ->
                numToCheck.toList().asSequence()
                    .map {
                        if (numToCheck.contains(abs(currentNum - it))) {
                            numToCheck.add(currentNum)
                            numToCheck.removeAt(0)
                            currentTotal+= currentNum
                            runningTotals.add(Pair(currentNum, currentTotal))
                            Pair(currentNum, true)
                        }
                        else Pair(currentNum, false)
                    }
                    .firstOrNull {it.second} ?: Pair(currentNum, false)
            }
            .first {!it.second}
             .first

        val contiguousRangeSum = runningTotals.asSequence()
            .mapIndexed { index, maybeRangeEnd ->
                if (maybeRangeEnd.second >= notMatchNum) {
                    val rangeStart = runningTotals.find { it.second == maybeRangeEnd.second - notMatchNum}
                    if (rangeStart != null) {
                        runningTotals.subList(runningTotals.indexOf(rangeStart) + 1, index)
                    } else null
                } else null
            }.firstOrNull {it != null}!!
            .map { it.first }
            .let {
                it.minOrNull()!! + it.maxOrNull()!!
            }

        return Pair(notMatchNum, contiguousRangeSum)
    }
}