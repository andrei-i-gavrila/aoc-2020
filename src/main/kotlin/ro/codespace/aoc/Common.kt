package ro.codespace.aoc

import com.google.common.io.Resources
import com.google.common.math.IntMath


fun read(file: String) = Resources.getResource(file).openStream().bufferedReader()

fun day(number: Int) = read("day$number.txt")
fun day(number: String) = read("day$number.txt")


fun Int.pow(n: Int): Int {
    return IntMath.pow(this, n)
}

