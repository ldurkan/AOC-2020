package advent2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 7")
class Day7Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @ParameterizedTest()
        @MethodSource("advent2020.Day7Test#examplesP1")
        fun `matches examples`(input: List<String>, expected: Int) {
            assertThat(Day7(input).solvePart1()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day7(Resources.resourceAsList("day7.input")).solvePart1())
                .isEqualTo(372)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @ParameterizedTest
        @MethodSource("advent2020.Day7Test#examplesP2")
        fun `matches examples`(input: List<String>, expected: Long) {
            assertThat(Day7(input).solvePart2()).isEqualTo(expected)
        }

        @Test
        fun `matches actual`() {
            assertThat(Day7(Resources.resourceAsList("day7.input")).solvePart2())
                .isEqualTo(8015)
        }
    }

    companion object {
        @JvmStatic
        fun examplesP1() : List<Arguments> = listOf(
            Arguments.of(
                listOf("light red bags contain 1 bright white bag, 2 muted yellow bags.",
                    "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                    "bright white bags contain 1 shiny gold bag.",
                    "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                    "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                    "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                    "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                    "faded blue bags contain no other bags.",
                    "dotted black bags contain no other bags."),
            4)
        )

        @JvmStatic
        fun examplesP2() : List<Arguments> = listOf(
            Arguments.of(listOf("light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."),
            32),
            Arguments.of(listOf("shiny gold bags contain 2 dark red bags.",
                    "dark red bags contain 2 dark orange bags.",
                    "dark orange bags contain 2 dark yellow bags.",
                    "dark yellow bags contain 2 dark green bags.",
                    "dark green bags contain 2 dark blue bags.",
                    "dark blue bags contain 2 dark violet bags.",
                    "dark violet bags contain no other bags."),
                126
            )
        )
    }
}

