package advent2020

fun main() {
    println(Day15("1,0,18,10,19,6").solvePart2())
}

class Day15(rawInput : String) {
    private val startNumbers = rawInput.split(",")
        .map { it.toInt() }

    fun solvePart1() : Int = findLastSpokenNumAtTurn(2020)

    fun solvePart2() : Int = findLastSpokenNumAtTurn(30000000)

    private fun findLastSpokenNumAtTurn(turn : Int) : Int {
        val spokenMap = startNumbers
            .mapIndexed { index, num -> num to listOf(index + 1) }
            .toMap()
            .toMutableMap()
        var lastSpoken = startNumbers.last()
        var currentTurn = startNumbers.size + 1

        while (currentTurn <= turn) {
            val turnsSpoken = spokenMap.getOrDefault(lastSpoken, listOf())
            lastSpoken = if (turnsSpoken.size == 1) 0
            else turnsSpoken.last() - turnsSpoken[turnsSpoken.lastIndex - 1]
            spokenMap.merge(lastSpoken, listOf(currentTurn)) { oldValue, newValue -> listOf(oldValue.last(), newValue.first())}

            currentTurn++
        }

        return lastSpoken
    }
}