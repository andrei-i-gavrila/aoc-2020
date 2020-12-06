package ro.codespace.aoc.day5

import ro.codespace.aoc.day
import kotlin.math.pow

fun String.parseBinary(oneBits: String): Int {
    var bitValue = 2.0.pow(this.length).toInt()
    return toList().fold(0) { value, bit ->
        bitValue /= 2
        if (bit in oneBits) value + bitValue else value
    }
}

fun main() {
    day(5).readLines().maxByOrNull {
        it.parseBinary("BR")
    }?.let { println(it.parseBinary("BR")) }

}