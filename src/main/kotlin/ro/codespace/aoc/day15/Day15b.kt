package ro.codespace.aoc.day15

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    day(15) {
        val starting = it.readLine().split(",").map { it.toLong() }
        val ages = mutableMapOf<Long, MutableList<Long>>()
        var last = 0L
        for (i in starting.indices) {
            if (starting[i] !in ages) ages[starting[i]] = mutableListOf()
            ages[starting[i]]!!.add(i.toLong())
            last = starting[i]
        }
        ages.log()
        for (i in starting.size until 30000000) {
            if (last in ages && ages[last]!!.size > 1) last = ages[last]!!.let {
                it[it.lastIndex] - it[it.lastIndex-1]
            }
            else last = 0
            if (last !in ages) ages[last] = mutableListOf()
            ages[last]!!.add(i.toLong())
        }

        last.log()

    }
}