package ro.codespace.aoc.day8

import ro.codespace.aoc.day

fun main() {
    listOf(day("8t"), day(8)).forEach {
        val lines = it.readLines()
        for (line in lines.indices) {
            if (lines[line].startsWith("acc")) continue
            val mutatedLines = lines.toMutableList()
            if (mutatedLines[line].startsWith("nop")) {
                mutatedLines[line] = mutatedLines[line].replace("nop", "jmp")
            } else {
                mutatedLines[line] = mutatedLines[line].replace("jmp", "nop")
            }
            val (acc, result) = runCode(mutatedLines)
            if (result) {
                println(acc)
                break
            }
        }
    }
}

private fun runCode(lines: List<String>): Pair<Int, Boolean> {
    val linesRan = mutableSetOf<Int>()
    var acc = 0
    var currentLine = 0

    while (currentLine !in linesRan) {
        if (currentLine > lines.lastIndex) return acc to true
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
    return acc to false
}