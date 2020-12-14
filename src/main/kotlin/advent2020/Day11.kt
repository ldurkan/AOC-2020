package advent2020

private typealias SeatState = Array<CharArray>

class Day11(private val rawInput : List<String>) {

    fun solvePart1() : Int = SeatResolver(rawInput)
        .apply { this.getConsistentState() }
        .numOccupiedSeats()

    fun solvePart2() : Int = SeatResolver(rawInput, immediate = false, seatsToBeOccupied = 5)
        .apply { this.getConsistentState() }
        .numOccupiedSeats()

    private class SeatResolver(input : List<String>, immediate: Boolean = true, private val seatsToBeOccupied: Int = 4) {
        private val maxY = input.size - 1
        private val maxX  = input.first().length - 1
        private var currentState = parse(input)
        private var previousState : SeatState? = null
        private val seatNeighbours = createSeatNeighboursMapping(currentState, immediate)

        fun getConsistentState() {
            while (!currentState.contentDeepEquals(previousState)) {
                val newState = applyRules(currentState)
                previousState = currentState
                currentState = newState
            }
        }

        fun numOccupiedSeats() : Int = currentState.flatMap { it.toList() }
            .count { it ==  OCCUPIED }

        private fun applyRules(state : SeatState) : SeatState =
            state.mapIndexed { y, row ->
                row.mapIndexed { x, char ->
                    val currentPoint = Point(x, y)
                    when (char) {
                        FLOOR -> char
                        UNOCCUPIED -> if (seatShouldBeOccupied(state, currentPoint)) OCCUPIED else UNOCCUPIED
                        OCCUPIED -> if (seatShouldBeFreed(state, currentPoint)) UNOCCUPIED else OCCUPIED
                        else -> throw IllegalStateException("Unknown char representation $char")
                    }
                }.toCharArray()
            }.toTypedArray()


        private fun seatShouldBeOccupied(state: SeatState, currentPoint: Point): Boolean =
            seatNeighbours.getValue(currentPoint)
                .all { state.get(it) != '#' }


        private fun seatShouldBeFreed(state: SeatState, currentPoint: Point) : Boolean =
            seatNeighbours.getValue(currentPoint)
                .count { state.get(it) == '#' } >= seatsToBeOccupied

        private fun createSeatNeighboursMapping(state: SeatState, immediate: Boolean) : Map<Point, List<Point>> {
            return state.mapIndexed { y, row ->
                row.mapIndexed { x, _ ->
                    Point(x, y)
                }}
                .flatten()
                .map { current -> current to current.getAllNeighbours()
                    .mapNotNull { if (immediate) it.inBounds() else getNextSeatPointInDirection(state, current, it)}
                }
                .toMap()
        }

        private fun getNextSeatPointInDirection(state: SeatState, start : Point, direction: Point) : Point? {
            val xVel = direction.x - start.x
            val yVel = direction.y - start.y

            var currentPoint : Point? = start
            do {
                currentPoint = Point(x = currentPoint!!.x + xVel, y = currentPoint.y + yVel).inBounds()
            }  while (currentPoint != null && state.get(currentPoint) == FLOOR)

            return currentPoint
        }

        private fun Point.inBounds() : Point? = if (this.x in 0..maxX && this.y in 0..maxY) this else null

        private fun SeatState.get(point: Point) : Char = this[point.y][point.x]

        companion object {
            private const val FLOOR = '.'
            private const val OCCUPIED = '#'
            private const val UNOCCUPIED = 'L'

            fun parse(input : List<String>) : SeatState = input
                .map { it.toCharArray() }
                .toTypedArray()
        }
    }
}