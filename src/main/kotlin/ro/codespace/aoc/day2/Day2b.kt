package ro.codespace.aoc.day2

import ro.codespace.aoc.day

fun main() {
    val passwords = day(2).readLines()
    passwords.count {
        val (lim, let, password)  = it.split(" ");
        val letter = let.substring(0, 1)


        val positions = lim.split("-").map { it.toInt() }

        positions.count { password[it-1].toString() == letter } == 1

    }.let { println(it) }
}