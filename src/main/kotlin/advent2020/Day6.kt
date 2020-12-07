package advent2020

class Day6(input : String) {
    private val answers = parse(input)

    fun solvePart1() : Int = answers.map { numUniqueQuestionsAnsweredYes(it) }.sum()

    fun solvePart2() : Int = answers.map { numQuestionsEveryoneSaidYes(it) }.sum()

    private fun numUniqueQuestionsAnsweredYes(groupAnswer: String) : Int =
        groupAnswer.groupBy { it }
            .count { it.key != '\n' }

    private fun numQuestionsEveryoneSaidYes(groupAnswer : String) : Int {
        val answerMap = groupAnswer.groupingBy { it }
            .eachCount()
        val numPeople = answerMap.getOrDefault('\n', 0) + 1

        return answerMap.filterValues { it == numPeople }
            .count()
    }

    //test

    companion object {
        private fun parse(input : String) : List<String> = input.split("\n\n")
    }
}