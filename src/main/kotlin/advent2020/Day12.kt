package advent2020

import kotlin.math.absoluteValue

class Day12(rawInput : List<String>) {
    private val instructions = rawInput.map { Instruction.parse(it) }

    fun solvePart1() : Int {
        var currentPoint = Point.ORIGIN
        var currentDirection = Direction.EAST
        instructions.forEach {
            val (action, amount) = it
            when (action) {
                NORTH -> currentPoint = currentPoint.moveNorth(amount)
                SOUTH -> currentPoint = currentPoint.moveSouth(amount)
                EAST -> currentPoint = currentPoint.moveEast(amount)
                WEST -> currentPoint = currentPoint.moveWest(amount)
                LEFT -> currentDirection = currentDirection.turnLeft(amount)
                RIGHT -> currentDirection = currentDirection.turnRight(amount)
                FORWARD -> currentPoint = currentDirection.movePointInDirection(currentPoint, amount)
                else -> throw IllegalArgumentException("Unknown instruction $action")
            }
        }

        return currentPoint.x.absoluteValue + currentPoint.y.absoluteValue
    }

    fun solvePart2() : Int {
        var shipLocation = Point.ORIGIN
        var waypointLocation = Point(x = 10, y = 1)

        instructions.forEach {
            val (action, amount) = it
            when (action) {
                NORTH -> waypointLocation = waypointLocation.moveNorth(amount)
                SOUTH -> waypointLocation = waypointLocation.moveSouth(amount)
                EAST -> waypointLocation = waypointLocation.moveEast(amount)
                WEST -> waypointLocation = waypointLocation.moveWest(amount)
                LEFT, RIGHT -> waypointLocation = waypointLocation.rotate(action == RIGHT, amount)
                FORWARD -> shipLocation = moveTowardsWaypoint(shipLocation, waypointLocation, amount)
                else -> throw IllegalArgumentException("Unknown instruction $action")
            }
        }

        return shipLocation.x.absoluteValue + shipLocation.y.absoluteValue
    }

    private fun moveTowardsWaypoint(shipLoc: Point, waypointLoc: Point, amount: Int) : Point =
        Point(x = shipLoc.x + waypointLoc.x * amount, y = shipLoc.y + waypointLoc.y * amount)

    companion object {
        private const val NORTH = 'N'
        private const val SOUTH = 'S'
        private const val EAST = 'E'
        private const val WEST = 'W'
        private const val LEFT =  'L'
        private const val RIGHT = 'R'
        private const val FORWARD = 'F'
    }

    private enum class Direction {
        WEST, NORTH, EAST, SOUTH;

        fun turnLeft(degrees: Int): Direction =
            turn(degrees) { currentIndex, indexChange -> currentIndex - indexChange }

        fun turnRight(degrees: Int): Direction =
            turn(degrees) { currentIndex, indexChange -> currentIndex + indexChange }

        fun movePointInDirection(point: Point, amount: Int): Point =
            when (this) {
                WEST -> point.moveWest(amount)
                NORTH -> point.moveNorth(amount)
                EAST -> point.moveEast(amount)
                SOUTH -> point.moveSouth(amount)
            }

        private fun turn(degrees: Int, turnChange: (currentIndex: Int, indexChange: Int) -> Int): Direction {
            val indexMove = degrees / 90
            val enumValues = values()

            return enumValues[Math.floorMod(turnChange(enumValues.indexOf(this), indexMove), enumValues.size)]
        }
    }

    private data class Instruction(val action : Char, val amount: Int) {
        companion object {
            fun parse(input : String) : Instruction = Instruction(input[0], input.drop(1).toInt())
        }
    }
}