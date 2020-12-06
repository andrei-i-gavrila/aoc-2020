package ro.codespace.aoc.day6

import ro.codespace.aoc.day

fun readAnswers(): MutableList<MutableList<String>> {
    val answers = mutableListOf<MutableList<String>>(mutableListOf())
    day(6).readLines().forEach {
        if (it.isEmpty()) {
            answers.add(mutableListOf())
        } else {
            answers.last().add(it)
        }
    }
    return answers
}

fun main() {
    readAnswers().map {
        it.flatMap { it.toList() }.toSet().size
    }.sum().let { println(it) }
}