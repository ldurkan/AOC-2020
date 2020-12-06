package advent2020

fun main() {
    println(Day6(Resources.resourceAsString("day6.input")).solvePart2())
}

class Day6(input : String) {
    private val answers = parse(input)

    fun solvePart1() : Int = answers.map {it.replace("\n", "")}
        .map { answer ->
        answer.groupBy { it }
            .keys
            .size
    }.sum()

    fun solvePart2() : Int =
        answers.map { answer ->
            val answerMap = answer.groupingBy { it }
                .eachCount()
            val numPeople = answerMap.getOrDefault('\n', 0) + 1
            answerMap.filterValues { it == numPeople }
                .count()
        }.sum()


    companion object {
        private fun parse(input : String) : List<String> = input.split("\n\n")
    }
}