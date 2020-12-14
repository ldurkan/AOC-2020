package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 13")
class Day13Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day13Test#examplesP1")
        fun `matches examples`(input: List<String>,  expected: Int) {
            assertThat(Day13(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day13(Resources.resourceAsList("day13.input")).solvePart1())
                .isEqualTo(156)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day13Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Long) {
            assertThat(Day13(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day13(Resources.resourceAsList("day13.input")).solvePart2())
                .isEqualTo(78883)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("939", "7,13,x,x,59,x,31,19"),
                295
            )
        )


        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("939", "7,13,x,x,59,x,31,19"),
                1068781
            ),
            Arguments.of(
                listOf("0", "17,x,13,19"),
                3417
            ),
            Arguments.of(
                listOf("0", "67,7,59,61"),
                754018
            ),
            Arguments.of(
                listOf("0", "67,x,7,59,61"),
                779210
            ),
            Arguments.of(
                listOf("0", "67,7,x,59,61"),
                1261476
            ),
            Arguments.of(
                listOf("0", "1789,37,47,1889"),
                1202161486
            ),
        )
    }
}

