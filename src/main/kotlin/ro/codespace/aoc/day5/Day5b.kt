package ro.codespace.aoc.day5

import ro.codespace.aoc.day

fun getMissing(values: List<Int>): Long {
    return (values.first().toLong() + values.last().toLong()) * (values.size+1) / 2 - values.sum()
}

fun main() {
        day(5).readLines().map {
            it.parseBinary("BR")
        }.sorted().let {
            println(getMissing(it))
        }
}