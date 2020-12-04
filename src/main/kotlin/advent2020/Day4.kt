package advent2020

private typealias Passport = List<Day4.Field>

class Day4(input : String) {
    private val passports = parse(input)

    fun solvePart1() : Int =
        passports.filter { fields -> fields.size == 8 ||
                (fields.size == 7 && fields.find { it.id == "cid" } == null)}
            .count()

    fun solvePart2() : Int = passports.filter { fields -> fields.size == 8 ||
            (fields.size == 7 && fields.find { it.id == "cid" } == null) }
        .filter { fields -> fields.all { it.isValidField() } }
        .count()

    companion object {
        fun parse(input : String) : List<Passport> =
            input.split("\n\n")
                .map { fields ->
                    fields.split(":", " ", "\n")
                        .chunked(2)
                        .map { Field(it[0], it[1]) }
                }
    }

    data class Field(val id : String, val value : String) {
        fun isValidField() : Boolean {
            return when (id) {
                "cid" -> true
                "byr" -> (1920..2002).contains(value.toInt())
                "iyr" -> (2010..2020).contains(value.toInt())
                "eyr" -> (2020..2030).contains(value.toInt())
                "hgt" -> {
                    val hgtType = value.takeLast(2)
                    val hgtValue = value.substring(0 until value.length - 2).toIntOrNull()
                    return when (hgtType) {
                        "cm" -> (150..193).contains(hgtValue ?: 0)
                        "in" -> (59..76).contains(hgtValue ?: 0)
                        else -> false
                    }
                }
                "hcl" -> value.length == 7
                        && value.take(1) == "#"
                        && value.substring(1).matches(hclRegex)
                "ecl" -> eclValues.contains(value)
                "pid" -> value.length == 9 && value.toIntOrNull() != null

                else -> throw IllegalArgumentException("Unknown field $id")
            }
        }

        companion object {
            val hclRegex = "[0-9a-f]+$".toRegex()
            val eclValues = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        }
    }


}