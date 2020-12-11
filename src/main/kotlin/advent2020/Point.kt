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
}