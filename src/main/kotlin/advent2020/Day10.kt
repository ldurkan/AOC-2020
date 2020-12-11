package advent2020


class Day10(rawInput : List<String>) {
    private val adapters = rawInput.map { it.toInt() }
        .sorted()

    fun solvePart1() : Int {
        val joltCounts = mutableMapOf<Int, Int>()
        //add device 3 jolt
        joltCounts[3] = 1
        var previousRating = 0

        adapters
            .forEach {
                joltCounts.merge(it - previousRating, 1) {old, _ -> old.inc()}
                previousRating = it
            }

        return joltCounts.getOrDefault(1, 0) * joltCounts.getOrDefault(3,0)
    }

    fun solvePart2() : Long {
        val pathsToAdapter = mutableMapOf(0 to 1L)

        adapters.plus(adapters.maxOrNull()!! + 3)
            .forEach {
                pathsToAdapter[it] = (1..3).map { previous ->
                    pathsToAdapter.getOrDefault(it - previous, 0)
                }.sum()
            }

        return pathsToAdapter.getValue(adapters.maxOrNull()!! + 3)
    }
}