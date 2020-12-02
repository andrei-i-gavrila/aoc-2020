package ro.codespace.aoc.day2

import ro.codespace.aoc.day

fun main() {
    val passwords = day(2).readLines()
    passwords.count {
        val (lim, let, password)  = it.split(" ");


        val (low, hi) = lim.split("-").map { it.toInt() }
        val letter = let.substring(0, 1)


        password.count { it.toString() == letter } in low..hi
    }.let { println(it) }
}