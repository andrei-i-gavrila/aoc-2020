package ro.codespace.aoc.day9

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    val input = day(9).readLines().map { it.trim().toLong() }

    val preambleLength = 25
    val target = solve(input, preambleLength)


    var i = 0
    var j = 2

    while (true) {
        val sum = input.subList(i, j).sum()
        if (sum < target) j++
        else if (sum > target) i++
        else break
    }

    input.subList(i, j).log()
    input.subList(i, j).let {
        val minOrNull = it.minOrNull()!!
        minOrNull.log()
        val maxOrNull = it.maxOrNull()!!
        maxOrNull.log()
        println(minOrNull + maxOrNull)
    }
    input.subList(i, j).sum().log()
}