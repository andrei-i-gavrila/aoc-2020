package ro.codespace.aoc.day13

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    listOf(day("13t"), day(13)).forEach {
        val (depTimeString, busesString) = it.readLines()
        val depTime = depTimeString.toInt()
        val buses = busesString.split(",").filter { it != "x" }.map { it.toInt() }

        buses.map { id: Int ->
            id to ((depTime / id) * id).let { if (it == depTime) it else it + id }
        }.minByOrNull { it.second }?.let {
            (it.second - depTime) * it.first
        }?.log()
    }
}