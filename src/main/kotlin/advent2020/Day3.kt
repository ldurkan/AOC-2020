package advent2020

typealias MapRow = String

class Day3(private val mapRows: List<MapRow>) {

    fun solvePart1() : Int = calculateNumTressOnSlope()

    fun solvePart2() : Long = slopesToCheck
        .map { calculateNumTressOnSlope(it).toLong() }
        .reduce {acc, i -> acc * i }

    private fun calculateNumTressOnSlope(slope : Slope = Slope(right = 3, down = 1)) : Int {
        var x = slope.right
        var treeCounter = 0
        for (y in slope.down until mapRows.size step slope.down) {
            if (mapRows[y].isPositionTree(x)) treeCounter++
            x += slope.right
        }

        return treeCounter
    }

    private fun String.isPositionTree(pos : Int) = this[pos % this.length] == TREE

    data class Slope(val right: Int, val down : Int)

    companion object {
        const val TREE = '#'

        val slopesToCheck = listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2)
        )
    }
}