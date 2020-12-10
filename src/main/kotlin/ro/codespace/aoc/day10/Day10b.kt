package ro.codespace.aoc.day10

import ro.codespace.aoc.day
import ro.codespace.aoc.log


fun ways(jolts: List<Int>): Long {
    val memo = mutableMapOf<Int, Long>(0 to 1)


    jolts.withIndex().drop(1).forEach {
        val possibleIndices = mutableListOf<Int>()
        if (it.index >= 1 && it.value - jolts[it.index - 1] <= 3) possibleIndices.add(it.index - 1)
        if (it.index >= 2 && it.value - jolts[it.index - 2] <= 3) possibleIndices.add(it.index - 2)
        if (it.index >= 3 && it.value - jolts[it.index - 3] <= 3) possibleIndices.add(it.index - 3)
        memo[it.index] = possibleIndices.map { memo[it]!! }.sum()
    }
    return memo[jolts.lastIndex]!!
}

fun main() {
    listOf(day("10t"), day(10)).forEach {
        val jolts = it.readLines().map { it.toInt() }.sorted()
        val allJolts = listOf(0) + jolts + listOf(jolts.last() + 3)

        ways(allJolts).log()
    }
}