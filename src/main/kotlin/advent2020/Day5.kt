package advent2020

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

class Day5(private val passes : List<String>) {

    fun solvePart1() : Int = passes.map { getSeatId(it)}
        .maxOrNull() ?: error("Couldn't find max seat ID")

    fun solvePart2(): Int =
        (passes.map { getSeatId(it) }
            .sorted()
            .zipWithNext()
            .groupBy { abs(it.first - it.second) }[2] ?: error("Couldn't find seat ID gap"))
            .first()
            .getIdInMiddle()

    private fun getSeatId(pass : String) : Int {
        val row =  findPosition(pass.take(ROW_PARTITION_SIZE), MAX_ROW_NUM)
        val column = findPosition(pass.takeLast(COL_PARTITION_SIZE), MAX_COL_NUM)

        return row * 8 + column
    }

    private fun findPosition(input : String, max : Int, min : Int = 0) : Int {
        var currentMax = max.toDouble()
        var currentMin = min.toDouble()

        for (pos in 0 until input.length - 1) {
            if (input[pos] in LOWER_HALF)
                currentMax = floor((currentMin + currentMax) / 2)
            else
                currentMin = ceil((currentMin + currentMax) / 2)
        }

        return if (input.last() in LOWER_HALF)
            currentMin.toInt()
        else
            currentMax.toInt()
    }

    private fun Pair<Int, Int>.getIdInMiddle()  : Int = this.first + 1

    companion object {
        private const val ROW_PARTITION_SIZE = 7
        private const val COL_PARTITION_SIZE = 3
        private const val MAX_ROW_NUM = 127
        private const val MAX_COL_NUM = 7

        private val LOWER_HALF = listOf('F', 'L')
    }
}