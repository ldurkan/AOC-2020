package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 14")
class Day14Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day14Test#examplesP1")
        fun `matches examples`(input: List<String>,  expected: Long) {
            assertThat(Day14(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day14(Resources.resourceAsList("day14.input")).solvePart1())
                .isEqualTo(13556564111697)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day14Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Long) {
            assertThat(Day14(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day14(Resources.resourceAsList("day14.input")).solvePart2())
                .isEqualTo(4173715962894)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                        "mem[8] = 11",
                        "mem[7] = 101",
                        "mem[8] = 0"),
                165
            )
        )


        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("mask = 000000000000000000000000000000X1001X",
                        "mem[42] = 100",
                        "mask = 00000000000000000000000000000000X0XX",
                        "mem[26] = 1"),
                208
            )
        )
    }
}

