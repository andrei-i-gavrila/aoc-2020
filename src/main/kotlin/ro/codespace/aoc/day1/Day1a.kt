package ro.codespace.aoc.day1

import ro.codespace.aoc.day

fun main() {
    val numbers = day(1).readLines().map { it.toInt() }.toSet()
    numbers.find { 2020 - it in numbers }?.let { println(it * (2020 - it)) }
}