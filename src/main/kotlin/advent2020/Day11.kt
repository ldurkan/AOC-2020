package advent2020

typealias SeatState = Array<CharArray>

fun main() {
    println(Day11(Resources.resourceAsList("day11.input")).solvePart2())
}

class Day11(rawInput : List<String>) {
    private val seating = SeatResolver(rawInput)

    fun solvePart1() : Int = seating
        .apply { this.getConsistentState() }
        .numOccupiedSeats()

    fun solvePart2() : Int = seating
        .apply { this.getConsistentState(immediate = false, seatsToBeOccupied = 5) }
        .numOccupiedSeats()

    private class SeatResolver(input : List<String>) {
        private val maxY = input.size - 1
        private val maxX  = input.first().length - 1
        private var currentState = parse(input)
        private var previousState : SeatState? = null

        fun getConsistentState(immediate: Boolean = true, seatsToBeOccupied: Int = 4) {
            while (!currentState.contentDeepEquals(previousState)) {
                val newState = applyRules(currentState, immediate, seatsToBeOccupied)
                previousState = currentState
                currentState = newState
            }
        }

        fun numOccupiedSeats() : Int = currentState.flatMap { it.toList() }
            .count { it ==  '#' }

        private fun applyRules(state : SeatState, immediate: Boolean, seatsToBeOccupied: Int) : SeatState {
            val newState = state.copyOf()

            return newState.mapIndexed { y, row ->
                row.mapIndexed { x, char ->
                    val currentPoint = Point(x , y)
                    when(char) {
                        '.' -> char
                        'L' -> if (seatShouldBeOccupied(state, currentPoint, immediate)) '#' else char
                        '#' -> if (seatShouldBeFreed(state, currentPoint, seatsToBeOccupied, immediate)) 'L' else char
                        else -> throw IllegalStateException("Unknown char representation $char")
                    }
                }.toCharArray()
            }.toTypedArray()
        }

        private fun seatShouldBeOccupied(state: SeatState, currentPoint: Point, immediate : Boolean): Boolean =
            currentPoint.getAllNeighbours()
                .map { if (immediate) it else getNextSeatPointInDirection(state, currentPoint, it) }
                .filter { it.x in 0..maxX && it.y in 0..maxY}
                .all { state.get(it) != '#' }


        private fun seatShouldBeFreed(state: SeatState, currentPoint: Point, seatsToBeOccupied : Int,
                                      immediate: Boolean) : Boolean =
            currentPoint.getAllNeighbours()
                .map { if (immediate) it else getNextSeatPointInDirection(state, currentPoint, it) }
                .filter { it.x in 0..maxX && it.y in 0..maxY}
                .count { state.get(it) == '#' } >= seatsToBeOccupied

        private fun getNextSeatPointInDirection(state: SeatState, start : Point, direction: Point) : Point {
            val xVel = direction.x - start.x
            val yVel = direction.y - start.y

            var currentPoint = direction
            while (currentPoint.x in 0..maxX && currentPoint.y in 0..maxY && state.get(currentPoint) == '.') {
                currentPoint = currentPoint.copy(x = currentPoint.x + xVel, y = currentPoint.y + yVel)
            }

            return currentPoint
        }

        private fun SeatState.get(point: Point) : Char = this[point.y][point.x]

        companion object {
            fun parse(input : List<String>) : SeatState = input
                .map { it.toCharArray() }
                .toTypedArray()
        }
    }
}