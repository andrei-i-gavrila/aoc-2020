package ro.codespace.aoc.day6

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups


fun main() {
    day(6).readLines().readGroups().map {
        it.flatMap { it.toList() }.toSet().size
    }.sum().log()
}