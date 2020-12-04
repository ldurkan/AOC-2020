package advent2020

private typealias Passport = Map<String, String>

class Day4(input: String) {
    private val passports = parse(input)

    fun solvePart1(): Int = passports
        .filter { fields -> fields.size == 8 }
        .count()

    fun solvePart2(): Int = passports
        .filter { fields -> fields.size == 8 }
        .filter { fields -> fields.allFieldsValid() }
        .count()

    private fun Map<String, String>.allFieldsValid(): Boolean = this.entries.all { it.isValidField() }

    private fun Map.Entry<String, String>.isValidField(): Boolean =
        when (key) {
            COUNTRY_ID -> true
            BIRTH_YEAR -> (1920..2002).contains(value.toInt())
            ISSUE_YEAR -> (2010..2020).contains(value.toInt())
            EXPIRATION_YEAR -> (2020..2030).contains(value.toInt())
            HEIGHT -> isValidHgtValue(value)
            HAIR_COLOUR -> value.matches(VALID_HAIR_COLOUR_REGEX)
            EYE_COLOUR -> eclValues.contains(value)
            PASSPORT_ID -> value.length == 9 && value.toIntOrNull() != null
            else -> throw IllegalArgumentException("Unknown field $key")
        }

    private fun isValidHgtValue(value: String): Boolean {
        val hgtType = value.takeLast(2)
        val hgtValue = value.substring(0 until value.length - 2).toIntOrNull()
        return when (hgtType) {
            HEIGHT_CENTIMETRES -> (150..193).contains(hgtValue ?: 0)
            HEIGHT_INCHES -> (59..76).contains(hgtValue ?: 0)
            else -> false
        }
    }

    companion object {
        private const val COUNTRY_ID = "cid"
        private const val BIRTH_YEAR = "byr"
        private const val ISSUE_YEAR = "iyr"
        private const val EXPIRATION_YEAR = "eyr"
        private const val HEIGHT = "hgt"
        private const val HAIR_COLOUR = "hcl"
        private const val EYE_COLOUR = "ecl"
        private const val PASSPORT_ID = "pid"
        private const val HEIGHT_CENTIMETRES = "cm"
        private const val HEIGHT_INCHES = "in"

        private val VALID_HAIR_COLOUR_REGEX = "[#][0-9a-f]{6}$".toRegex()
        private val eclValues = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        private fun parse(input: String): List<Passport> =
            input.split("\n\n")
                .map { rawFields ->
                    rawFields.split(":", " ", "\n")
                        .chunked(2)
                        .map { it[0] to it[1] }
                        .toMap()
                        .toMutableMap()
                        .apply { this.putIfAbsent(COUNTRY_ID, "") }
                }
    }
}
