package ro.codespace.aoc.day16

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups

fun main() {
    day(16) {
        val (rules, myTicket, otherTickets) = it.readLines().readGroups()

        val ranges = rules.flatMap {
            it.substringAfter(": ")
                    .split(" or ")
                    .map {
                        it.split("-")
                                .map { it.toInt() }
                                .let { it[0]..it[1] }
                    }
        }

        otherTickets.drop(1).flatMap {
            it.split(",").map { it.toInt() }
        }.filter {
            ranges.none { rule -> rule.contains(it) }
        }.sum().log()

    }
}