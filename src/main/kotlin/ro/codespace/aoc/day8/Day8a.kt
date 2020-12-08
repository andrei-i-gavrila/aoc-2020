package ro.codespace.aoc.day8

import ro.codespace.aoc.day

fun main() {
    listOf(day("8t"), day(8)).forEach {
        var acc = 0
        var currentLine = 0
        val linesRan = mutableSetOf<Int>()

        val lines = it.readLines()
        while (currentLine !in linesRan) {
            linesRan.add(currentLine)
            val (op, arg) = lines[currentLine].split(" ")

            when (op) {
                "nop" -> currentLine++
                "jmp" -> currentLine += arg.toInt()
                "acc" -> {
                    acc += arg.toInt()
                    currentLine++
                }
            }
        }
        println(acc)
    }
}