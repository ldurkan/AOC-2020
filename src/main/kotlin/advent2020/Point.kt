package advent2020

data class Point(val x : Int, val y : Int) {

    fun getAllNeighbours() : List<Point> = listOf(
        this.copy(y = y + 1),
        this.copy(x = x + 1, y = y + 1),
        this.copy(x = x + 1),
        this.copy(y = y - 1, x = x + 1),
        this.copy(y = y - 1),
        this.copy(x = x - 1, y = y - 1),
        this.copy(x = x - 1),
        this.copy(x = x - 1, y = y + 1)
    )

    fun moveNorth(amount : Int) = this.copy(x = x, y = y + amount)

    fun moveSouth(amount : Int) = this.copy(x = x, y = y - amount)

    fun moveEast(amount : Int) = this.copy(x = x + amount, y = y)

    fun moveWest(amount : Int) = this.copy(x = x - amount, y = y)

    companion object {
        val ORIGIN = Point(0, 0)
    }
}