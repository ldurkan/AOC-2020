package advent2020

class Day1(input : List<String>) {
    private val entries = input.map { it.toInt() }

    fun solvePart1() : Int {
        for (firstEntry in entries) {
            val requiredSecondEntry = NUM_TO_SUM_TO - firstEntry
            if (entries.contains(requiredSecondEntry)) return requiredSecondEntry * firstEntry
        }

        return -1
    }

    fun solvePart2() : Int {
        val sortedEntries = entries.sortedDescending()

        for (entry in sortedEntries) {
            for (index in sortedEntries.size-1 downTo 0) {
                val secondEntry = sortedEntries[index]
                if (entry + secondEntry > NUM_TO_SUM_TO) break

                val thirdEntryRequired = NUM_TO_SUM_TO - entry - secondEntry
                if (sortedEntries.contains(thirdEntryRequired)) return entry * secondEntry * thirdEntryRequired
            }
        }

        return -1
    }

    companion object {
        private const val NUM_TO_SUM_TO = 2020
    }
}