package advent2020

fun main() {
    println(Day1(Resources.resourceAsList("day1.input")).solvePart2())
}

class Day1(input : List<String>) {
    private val entries = input.map { it.toInt() }

    fun solvePart1() : Int {
        val sortedEntries = entries.sortedDescending()

        for (entry in sortedEntries) {
            for (index in sortedEntries.size-1 downTo 0) {
                val secondEntry = sortedEntries[index]
                if (entry + secondEntry > 2020) break
                else if (entry + secondEntry == 2020) return entry * secondEntry
            }
        }

        return -1
    }

    fun solvePart2() : Int {
        val sortedEntries = entries.sortedDescending()

        for (entry in sortedEntries) {
            for (index in sortedEntries.size-1 downTo 0) {
                val secondEntry = sortedEntries[index]
                if (entry + secondEntry > 2020) break

                val thirdEntryRequired = 2020 - entry - secondEntry
                if (sortedEntries.contains(thirdEntryRequired)) return entry * secondEntry * thirdEntryRequired
            }
        }

        return -1
    }
}