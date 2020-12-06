package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 6")
class Day6Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day6Test#examplesP1")
        fun `matches examples`(input: String, expected: Int) {
            assertThat(Day6(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day6(Resources.resourceAsString("day6.input")).solvePart1())
                .isEqualTo(6748)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day6Test#examplesP2")
        fun `matches examples`(input: String, expected: Long) {
            assertThat(Day6(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day6(Resources.resourceAsString("day6.input")).solvePart2())
                .isEqualTo(3445)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of( "abc\n" +
                    "\n" +
                    "a\n" +
                    "b\n" +
                    "c\n" +
                    "\n" +
                    "ab\n" +
                    "ac\n" +
                    "\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "\n" +
                    "b",
            11)
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of( "abc\n" +
                    "\n" +
                    "a\n" +
                    "b\n" +
                    "c\n" +
                    "\n" +
                    "ab\n" +
                    "ac\n" +
                    "\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "\n" +
                    "b",
                6)
        )
    }
}

