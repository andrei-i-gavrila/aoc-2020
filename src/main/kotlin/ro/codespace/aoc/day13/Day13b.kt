package ro.codespace.aoc.day13

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun multInv(a: Long, b: Long): Long {
    if (b == 1L) return 1L
    var aa = a
    var bb = b
    var x0 = 0L
    var x1 = 1L
    while (aa > 1L) {
        val q = aa / bb
        var t = bb
        bb = aa % bb
        aa = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }
    if (x1 < 0L) x1 += b
    return x1
}

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L
    for (i in n.indices) {
        val p = prod / n[i]
        sum += a[i] * multInv(p, n[i]) * p
    }
    return sum % prod
}
fun main() {
    listOf(day("13t"), day(13)).forEach {
        val (depTimeString, busesString) = it.readLines()
        val buses = busesString.split(",").withIndex().filter { it.value != "x" }.map { it.value.toLong() to it.index.toLong() }

        buses.log()

        chineseRemainder(buses.map { it.first }, buses.map { it.first - it.second }).log()
    }
}