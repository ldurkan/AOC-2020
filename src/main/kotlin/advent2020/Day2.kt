package advent2020

class Day2(input : List<String>) {
    private val passwordWithPolicies = input.map { PasswordWithPolicy.parse(it) }

    fun solvePart1(): Int = passwordWithPolicies.count { it.passwordMeetsPolicy() }

    fun solvePart2() : Int = passwordWithPolicies.count { it.passwordMeetsNewPolicy()}

    private data class PasswordWithPolicy(
        private val policyRange: IntRange,
        private val policyCharacter: Char,
        private val password: String) {

        fun passwordMeetsPolicy(): Boolean {
            val passwordCharMap = password.groupingBy { it }
                .eachCount()

            return policyRange.contains(passwordCharMap.getOrDefault(policyCharacter, 0))
        }

        fun passwordMeetsNewPolicy() : Boolean =
            listOf(password[policyRange.first - 1], password[policyRange.last - 1])
                .filter { it == policyCharacter }
                .count() == 1

        companion object {
            //e.g. 1-3 a: abcde
            fun parse(input: String): PasswordWithPolicy {
                val args = input.split(" ")
                val policyRange = args[0].split("-")
                    .let { it[0].toInt()..it[1].toInt() }
                val policyChar = args[1].first()

                return PasswordWithPolicy(policyRange, policyChar, args[2])
            }
        }
    }
}