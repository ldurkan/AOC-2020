package advent2020


class Day10(rawInput : List<String>) {
    private val adapters = rawInput.map { it.toInt() }
        .sorted()
        .let { it.plus(it.maxOrNull()!! + 3) }

    fun solvePart1() : Int =
        (listOf(0) + adapters)
            .zipWithNext()
            .map { it.second - it.first }
            .groupingBy { it }
            .eachCount()
            .let { it.getValue(1) * it.getValue(3) }

    fun solvePart2() : Long {
        val pathsToAdapter = mutableMapOf(0 to 1L)

        adapters
            .forEach {
                pathsToAdapter[it] = (1..3).map { previous ->
                    pathsToAdapter.getOrDefault(it - previous, 0)
                }.sum()
            }

        return pathsToAdapter.getValue(adapters.last())
    }
}