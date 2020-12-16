package ro.codespace.aoc.day16

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups

fun main() {
    day(16, "t2") {
        val (rules, myTicket, otherTickets) = it.readLines().readGroups()

        val ruleList = rules.map {
            it.substringBefore(":") to it.substringAfter(": ")
                    .split(" or ")
                    .map {
                        it.split("-")
                                .map { it.toInt() }
                                .let { it[0]..it[1] }
                    }
        }

        val ownTicket = myTicket.drop(1).map { it.split(",").map { it.toInt() } }.first()

        val validTickets = otherTickets.drop(1).map {
            it.split(",").map { it.toInt() }
        }.filter { fields ->
            fields.all { field ->
                ruleList.any { rule ->
                    rule.second.any { range -> range.contains(field) }
                }
            }
        } + listOf(ownTicket)


        validTickets.log()

        val fields = mutableSetOf<Int>()

        ruleList.map { rule ->
            rule.first to validTickets[0].indices.filter { index ->
                validTickets.map { it[index] }.all { rule.second.any { range -> range.contains(it) } }
            }.toSet()
        }.sortedBy { it.second.size }.map {
            val availableFields = it.second.subtract(fields)
            if (availableFields.size == 1) {
                val chosenField = availableFields.first()
                fields.add(chosenField)
                it.first to chosenField
            } else {
                error("ambiguity")
            }
        }.filter { it.first.startsWith("departure") }.map { ownTicket[it.second].toLong() }.fold(1L, Long::times).log()

    }
}