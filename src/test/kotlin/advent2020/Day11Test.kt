package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 11")
class Day11Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day11Test#examplesP1")
        fun `matches examples`(input: List<String>,  expected: Int) {
            assertThat(Day11(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day11(Resources.resourceAsList("day11.input")).solvePart1())
                .isEqualTo(2283)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day11Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day11(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day11(Resources.resourceAsList("day11.input")).solvePart2())
                .isEqualTo(2054)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("L.LL.LL.LL",
                        "LLLLLLL.LL",
                        "L.L.L..L..",
                        "LLLL.LL.LL",
                        "L.LL.LL.LL",
                        "L.LLLLL.LL",
                        "..L.L.....",
                        "LLLLLLLLLL",
                        "L.LLLLLL.L",
                        "L.LLLLL.LL"),
                37
            )
        )


        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("L.LL.LL.LL",
                    "LLLLLLL.LL",
                    "L.L.L..L..",
                    "LLLL.LL.LL",
                    "L.LL.LL.LL",
                    "L.LLLLL.LL",
                    "..L.L.....",
                    "LLLLLLLLLL",
                    "L.LLLLLL.L",
                    "L.LLLLL.LL"),
                26
            )
        )
    }
}

