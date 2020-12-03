package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 3")
class Day3Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day3Test#examplesP1")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day3(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day3(Resources.resourceAsList("day3.input")).solvePart1())
                .isEqualTo(232)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day3Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Long) {
            assertThat(Day3(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day3(Resources.resourceAsList("day3.input")).solvePart2())
                .isEqualTo(3952291680L)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("..##.......", "#...#...#..", ".#....#..#.", "..#.#...#.#", ".#...##..#.", "..#.##.....",
                    ".#.#.#....#", ".#........#", "#.##...#...", "#...##....#", ".#..#...#.#"),
                7
            )
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(
                listOf("..##.......", "#...#...#..", ".#....#..#.", "..#.#...#.#", ".#...##..#.", "..#.##.....",
                    ".#.#.#....#", ".#........#", "#.##...#...", "#...##....#", ".#..#...#.#"),
                336L
            )
        )
    }
}

