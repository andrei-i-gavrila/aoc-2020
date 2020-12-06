package ro.codespace.aoc.day6

fun main() {
    readAnswers().map {
        it.map { it.toList().toSet() }.reduceRight {set, acc -> set.intersect(acc) }.size
    }.sum().let { println(it) }
}