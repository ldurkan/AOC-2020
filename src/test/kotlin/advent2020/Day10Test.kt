package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day10Test#examplesP1")
        fun `matches examples`(input: List<String>,  expected: Int) {
            assertThat(Day10(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day10(Resources.resourceAsList("day10.input")).solvePart1())
                .isEqualTo(3000)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day10Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Long) {
            assertThat(Day10(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day10(Resources.resourceAsList("day10.input")).solvePart2())
                .isEqualTo(193434623148032)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("16",
                        "10",
                        "15",
                        "5",
                        "1",
                        "11",
                        "7",
                        "19",
                        "6",
                        "12",
                        "4"),
                35),
            Arguments.of(
                listOf("28",
                        "33",
                        "18",
                        "42",
                        "31",
                        "14",
                        "46",
                        "20",
                        "48",
                        "47",
                        "24",
                        "23",
                        "49",
                        "45",
                        "19",
                        "38",
                        "39",
                        "11",
                        "1",
                        "32",
                        "25",
                        "35",
                        "8",
                        "17",
                        "7",
                        "9",
                        "4",
                        "2",
                        "34",
                        "10",
                        "3"),
                220
            )
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("16",
                    "10",
                    "15",
                    "5",
                    "1",
                    "11",
                    "7",
                    "19",
                    "6",
                    "12",
                    "4"),
                8),
            Arguments.of(
                listOf("28",
                    "33",
                    "18",
                    "42",
                    "31",
                    "14",
                    "46",
                    "20",
                    "48",
                    "47",
                    "24",
                    "23",
                    "49",
                    "45",
                    "19",
                    "38",
                    "39",
                    "11",
                    "1",
                    "32",
                    "25",
                    "35",
                    "8",
                    "17",
                    "7",
                    "9",
                    "4",
                    "2",
                    "34",
                    "10",
                    "3"),
                19208
            )
        )
    }
}

