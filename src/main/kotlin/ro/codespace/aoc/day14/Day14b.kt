package ro.codespace.aoc.day14


import ro.codespace.aoc.day
import ro.codespace.aoc.log
import java.util.*

fun readMapMask(mask: String): Map<Int, Char> {
    return mask.split(" = ")[1].toList().reversed().withIndex().filter { it.value != '0' }.map { it.index to it.value }.toMap()
}

fun getAllAddresses(address: Long, mapMask: Map<Int, Char>): List<Long> {
    val rawAddresses = address.toString(2).padStart(36, '0').reversed().mapIndexed { i, c ->
        if (i in mapMask) mapMask[i]!! else c
    }.let { Stack<List<Char>>().apply { add(it) } }

    val addresses = mutableListOf<Long>()

    while (rawAddresses.isNotEmpty()) {
        val rawAddress = rawAddresses.pop()
        var wasProcessed = false
        for (i in rawAddress.indices) {
            if (rawAddress[i] == 'X') {
                wasProcessed = true
                listOf('0', '1').map { newBit -> rawAddress.toMutableList().also { it[i] = newBit }.toList() }.forEach { rawAddresses.push(it) }
                break
            }
        }
        if (!wasProcessed) {
            addresses.add(rawAddress.joinToString("").toLong(2))
        }
    }
    return addresses
}

fun main() {
    day(14, "t2") {
        var mask = readMapMask(it.readLine())
        val memory = mutableMapOf<Long, Long>()
        var lineCount = 0
        while (true) {
            println(lineCount++)
            val line = it.readLine()
            if (line == null) {
                memory.values.sum().log()
                break
            } else if (line.startsWith("mem")) {
                val match = Regex("mem\\[(\\d+)] = (\\d+)").matchEntire(line)!!
                val address = match.groups[1]!!.value
                val str = match.groups[2]!!.value
                getAllAddresses(address.toLong(), mask).forEach {
                    memory[it] = str.toLong()
                }
            } else {
                mask = readMapMask(line)
            }
        }
    }
}