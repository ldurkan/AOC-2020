package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 15")
class Day15Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day15Test#examplesP1")
        fun `matches examples`(input: String,  expected: Int) {
            assertThat(Day15(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day15("1,0,18,10,19,6").solvePart1())
                .isEqualTo(441)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day15Test#examplesP2")
        fun `matches examples`(input: String, expected: Int) {
            assertThat(Day15(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day15("1,0,18,10,19,6").solvePart2())
                .isEqualTo(10613991)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of("0,3,6", 436),
            Arguments.of("1,3,2", 1),
            Arguments.of("2,1,3", 10),
            Arguments.of("1,2,3", 27),
            Arguments.of("2,3,1", 78),
            Arguments.of("3,2,1", 438),
            Arguments.of("3,1,2", 1836)
        )


        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of("0,3,6", 175594),
            Arguments.of("1,3,2", 2578),
            Arguments.of("2,1,3", 3544142),
            Arguments.of("1,2,3", 261214),
            Arguments.of("2,3,1", 6895259),
            Arguments.of("3,2,1", 18),
            Arguments.of("3,1,2", 362)
        )
    }
}

