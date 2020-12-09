package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 9")
class Day9Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day9Test#examplesP1")
        fun `matches examples`(input: List<String>, preambleSize: Int,  expected: Long) {
            assertThat(Day9(input).solvePart1(preambleSize)).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day9(Resources.resourceAsList("day9.input")).solvePart1())
                .isEqualTo(248131121)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day9Test#examplesP2")
        fun `matches examples`(input: List<String>, preambleSize: Int, expected: Long) {
            assertThat(Day9(input).solvePart2(preambleSize)).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day9(Resources.resourceAsList("day9.input")).solvePart2())
                .isEqualTo(31580383)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("35",
                        "20",
                        "15",
                        "25",
                        "47",
                        "40",
                        "62",
                        "55",
                        "65",
                        "95",
                        "102",
                        "117",
                        "150",
                        "182",
                        "127",
                        "219",
                        "299",
                        "277",
                        "309",
                        "576"),
            5,
            127L)
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("35",
                    "20",
                    "15",
                    "25",
                    "47",
                    "40",
                    "62",
                    "55",
                    "65",
                    "95",
                    "102",
                    "117",
                    "150",
                    "182",
                    "127",
                    "219",
                    "299",
                    "277",
                    "309",
                    "576"),
                5,
                62L)
        )
    }
}

