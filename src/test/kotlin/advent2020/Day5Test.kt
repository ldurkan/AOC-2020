package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 5")
class Day5Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day5Test#examplesP1")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day5(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day5(Resources.resourceAsList("day5.input")).solvePart1())
                .isEqualTo(888)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `matches actual`() {
            assertThat(Day5(Resources.resourceAsList("day5.input")).solvePart2())
                .isEqualTo(522)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(listOf("FBFBBFFRLR"), 357),
            Arguments.of(listOf("BFFFBBFRRR"), 567),
            Arguments.of(listOf("FFFBBBFRRR"), 119),
            Arguments.of(listOf("BBFFBBFRLL"), 820),
        )
    }
}

