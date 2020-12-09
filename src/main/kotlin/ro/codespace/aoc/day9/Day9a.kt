package ro.codespace.aoc.day9

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun check(preamble: Set<Long>, target: Long): Boolean {
    return preamble.any {
        target - it in preamble
    }
}

fun main() {
    val input = day(9).readLines().map { it.trim().toLong() }

    val preambleLength = 25


    solve(input, preambleLength).log()
}

fun solve(input: List<Long>, preambleLength: Int): Long {
    return input.windowed(preambleLength + 1).find {
        !check(it.subList(0, preambleLength).toSet(), it.last())
    }!!.last()
}