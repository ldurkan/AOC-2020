package advent2020

private typealias Instruction = String

class Day8(private val instructions : List<Instruction>) {

    fun solvePart1() : Int = runInstructions(instructions).accumulator

    fun solvePart2() : Int = instructions.mapIndexed { index, instr -> Pair(index, instr) }
        .filterNot { it.second.startsWith(CMND_ACCUMULATOR) }
        .asSequence()
        .map { (instrPos, instr) ->
            val instructionsCopy = instructions.toMutableList()
            instructionsCopy.removeAt(instrPos)
            instructionsCopy.add(instrPos, instr.flipInstruction())

            runInstructions(instructionsCopy)
        }
        .first { it.pointerPos == instructions.size }
        .accumulator

    private fun Instruction.flipInstruction() : Instruction {
        val (instrType, amount) = this.split(" ")

        return if (instrType == CMND_JUMP) "$CMND_NO_OP $amount"
        else "$CMND_JUMP $amount"
    }

    private fun runInstructions(instructions : List<Instruction>) : Result {
        val executedInstructions = mutableSetOf<Int>()
        var accumulator = 0

        var pointerPos = 0
        while (pointerPos < instructions.size &&  pointerPos >= 0 && !executedInstructions.contains(pointerPos)) {
            executedInstructions.add(pointerPos)

            val currentInstruction = instructions[pointerPos]
            val args = currentInstruction.split(" ")
            val instrType = args[0]
            val amount = args[1].toInt()

            if (instrType == CMND_ACCUMULATOR) accumulator += amount

            if (instrType == CMND_JUMP) pointerPos += amount
            else pointerPos++
        }

        return Result(accumulator, pointerPos)
    }

    private data class Result(val accumulator : Int, val pointerPos : Int)

    companion object {
        const val CMND_JUMP = "jmp"
        const val CMND_ACCUMULATOR = "acc"
        const val CMND_NO_OP = "nop"
    }
}