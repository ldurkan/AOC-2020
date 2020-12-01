package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 1")
class Day1Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest
        @MethodSource("advent2020.Day1Test#examplesP1")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day1(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day1(Resources.resourceAsList("day1.input")).solvePart1())
                .isEqualTo(158916)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day1Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day1(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day1(Resources.resourceAsList("day1.input")).solvePart2())
                .isEqualTo(165795564)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("1721", "979", "366", "299", "675", "1456"),
                514579
            )
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("1721", "979", "366", "299", "675", "1456"),
                241861950
            )
        )
    }
}

