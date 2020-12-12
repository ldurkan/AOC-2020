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

    fun moveNorth(amount : Int) = this.copy(y = y + amount)

    fun moveSouth(amount : Int) = this.copy(y = y - amount)

    fun moveEast(amount : Int) = this.copy(x = x + amount)

    fun moveWest(amount : Int) = this.copy(x = x - amount)

    fun rotate(turnRight : Boolean, amount: Int) : Point =
        when(val rotation = Math.floorMod(if(turnRight) amount else -amount, 360)) {
            0 -> this
            90 -> Point(x = y, y = -x)
            180 -> Point(x = -x, y = -y)
            270 -> Point(x = -y, y = x)
            else -> throw  IllegalStateException("Unknown degree change $rotation")
        }

    companion object {
        val ORIGIN = Point(0, 0)
    }
}