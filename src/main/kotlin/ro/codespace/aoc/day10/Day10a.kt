package ro.codespace.aoc.day10

import ro.codespace.aoc.day

fun main() {
    listOf(day(10), day("10t")).forEach {
        val jolts = it.readLines().map { it.toInt() }.sorted()
        val allJolts = listOf(0) + jolts + listOf(jolts.last() + 3)
        var oneCount = 0
        var threeCount = 0
        allJolts.windowed(2).forEach {
            when (it[1] - it[0]) {
                1 -> oneCount++
                3 -> threeCount++
            }
        }
        println(oneCount * threeCount)
    }
 }