package ro.codespace.aoc.day1

import ro.codespace.aoc.day

fun main() {
    val numbers = day(1).readLines().map { it.toInt() }
    numbers
        .flatMap { a -> numbers.map { listOf(a, it) } }
        .flatMap { ab -> numbers.map { ab + it } }
        .find { it.sum() == 2020 }
        ?.let { println(it.reduce(Int::times)) }
}