package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day12Test#examplesP1")
        fun `matches examples`(input: List<String>,  expected: Int) {
            assertThat(Day12(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day12(Resources.resourceAsList("day12.input")).solvePart1())
                .isEqualTo(1565)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day12Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day12(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day12(Resources.resourceAsList("day12.input")).solvePart2())
                .isEqualTo(78883)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("F10",
                        "N3",
                        "F7",
                        "R90",
                        "F11"),
                25
            )
        )


        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("F10",
                    "N3",
                    "F7",
                    "R90",
                    "F11"),
                286
            )
        )
    }
}

