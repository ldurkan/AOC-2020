package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 2")
class Day2Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day2Test#examplesP1")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day2(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day2(Resources.resourceAsList("day2.input")).solvePart1())
                .isEqualTo(643)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day2Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day2(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day2(Resources.resourceAsList("day2.input")).solvePart2())
                .isEqualTo(388)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"),
                2
            )
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"),
                1
            )
        )
    }
}

