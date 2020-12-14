package advent2020

fun main() {
    println(Day13(Resources.resourceAsList("day13.input")).solvePart1())
}

class Day13(rawInput : List<String>) {
    private val departTime =  rawInput[0].toInt()
    private val busTimes = parseBusTimes(rawInput[1])

    fun solvePart1() : Int = getEarliestBusTime()
        .let { it.busId * it.minutesWaited }

    fun solvePart2() : Long {
        //to do
        return -1L
    }

    private fun getEarliestBusTime() : Result {
        var leaveTime = departTime

        while (true) {
            busTimes.map { it.first }
                .forEach {
                if (leaveTime.rem(it) == 0) return Result(it, leaveTime - departTime)
            }
            leaveTime++
        }
    }

    companion object {
        fun parseBusTimes(input : String) : List<Pair<Int, Int>> =
            input.split(",")
                .filterNot { it == "x" }
                .mapIndexed { index, num -> num.toInt() to index }
    }

    private data class Result(val busId : Int, val minutesWaited : Int)
}