package advent2020

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    println(Day5(Resources.resourceAsList("day5.input")).solvePart2())
}

class Day5(private val passes : List<String>) {

    fun solvePart1() : Int = passes.map { getSeatId(it)}
        .maxOrNull() ?: error("Couldn't find max seat ID")

    fun solvePart2(): Int =
        passes.map { getSeatId(it) }
            .sorted()
            .zipWithNext()
            .map { it to abs(it.first - it.second) }
            .first { it.second == 2 }.first
            .first + 1


    private fun getSeatId(pass : String) : Int {
        val row =  findPosition(pass.take(7), 127)
        val column = findPosition(pass.takeLast(3), 7)

        return row * 8 + column
    }

    private fun findPosition(input : String, max : Int, min : Int = 0) : Int {
        var currentMax = max.toDouble()
        var currentMin = min.toDouble()

        for (index in 0 until input.length - 1) {
            if (input[index] == LOWER_ROW || input[index] == LOWER_COL) currentMax = floor((currentMin + currentMax) / 2)
            else currentMin = ceil((currentMin + currentMax) / 2)
        }

        return if (input.last() == LOWER_COL || input.last() == LOWER_ROW) currentMin.toInt() else currentMax.toInt()
    }

    companion object {
        const val LOWER_ROW = 'F'
        const val LOWER_COL = 'L'
    }
}