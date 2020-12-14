package ro.codespace.aoc.day14

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun readMask(mask: String): (index: Int, value: Char) -> Char {
    val maskMap = mask.split(" = ")[1].toList().reversed().withIndex().filter { it.value != 'X' }.map { it.index to it.value }.toMap()
    return { index, value ->
        if (index in maskMap) maskMap[index]!! else value
    }
}

fun main() {
    day(14) {
        var mask = readMask(it.readLine())
        val memory = mutableMapOf<Int, String>()
        while (true) {
            val line = it.readLine()
            if (line == null) {
                memory.values.sumByDouble { it.toLong(2).toDouble() }.toLong().log()
                break
            } else if (line.startsWith("mem")) {
                val match = Regex("mem\\[(\\d+)] = (.*)").matchEntire(line)!!
                val address = match.groups[1]!!.value.toInt()
                val str = match.groups[2]!!.value.toInt().toString(2).padStart(36, '0')
                memory[address] = str.reversed().mapIndexed(mask).reversed().joinToString("")
            } else {
                mask = readMask(line)
            }
        }
    }
}