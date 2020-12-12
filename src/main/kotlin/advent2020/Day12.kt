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
                'N' -> currentPoint = currentPoint.moveNorth(amount)
                'S' -> currentPoint = currentPoint.moveSouth(amount)
                'E' -> currentPoint = currentPoint.moveEast(amount)
                'W' -> currentPoint = currentPoint.moveWest(amount)
                'L' -> currentDirection = currentDirection.turnLeft(amount)
                'R' -> currentDirection = currentDirection.turnRight(amount)
                'F' -> currentPoint = moveInDirection(currentPoint, currentDirection, amount)
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
                'N' -> waypointLocation = waypointLocation.moveNorth(amount)
                'S' -> waypointLocation = waypointLocation.moveSouth(amount)
                'E' -> waypointLocation = waypointLocation.moveEast(amount)
                'W' -> waypointLocation = waypointLocation.moveWest(amount)
                'L','R' -> waypointLocation = rotateWaypoint(waypointLocation, action == 'R', amount)
                'F' -> shipLocation = moveTowardsWaypoint(shipLocation, waypointLocation, amount)
                else -> throw IllegalArgumentException("Unknown instruction $action")
            }
        }

        return shipLocation.x.absoluteValue + shipLocation.y.absoluteValue
    }

    private fun rotateWaypoint(waypointLoc : Point, turnRight : Boolean, amount: Int) : Point =
        when(val rotation = Math.floorMod(if(turnRight) amount else -amount, 360)) {
            0 -> waypointLoc
            90 -> Point(x = waypointLoc.y, y = -waypointLoc.x)
            180 -> Point(x = -waypointLoc.x, y = -waypointLoc.y)
            270 -> Point(x = -waypointLoc.y, y = waypointLoc.x)
            else -> throw  IllegalStateException("Unknown degree change $rotation")
        }

    private fun moveTowardsWaypoint(shipLoc: Point, waypointLoc: Point, amount: Int) : Point =
        Point(x = shipLoc.x + waypointLoc.x * amount, y = shipLoc.y + waypointLoc.y * amount)

    private fun moveInDirection(point: Point, direction: Direction, amount: Int) : Point =
        when(direction) {
            Direction.WEST -> point.moveWest(amount)
            Direction.NORTH -> point.moveNorth(amount)
            Direction.EAST -> point.moveEast(amount)
            Direction.SOUTH -> point.moveSouth(amount)
        }

    private enum class Direction {
        WEST, NORTH, EAST, SOUTH;

        fun turnLeft(degrees : Int) : Direction = turn(degrees) {currentIndex, indexChange -> currentIndex - indexChange }

        fun turnRight(degrees : Int) : Direction = turn(degrees) {currentIndex, indexChange -> currentIndex + indexChange }

        private fun turn(degrees : Int, turnChange : (currentIndex : Int, indexChange : Int) -> Int) : Direction {
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