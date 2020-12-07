package ro.codespace.aoc.day7

import ro.codespace.aoc.day
import java.util.*

fun main() {
    listOf(day("7test"), day(7)).forEach {
        val graph = mutableMapOf<String, MutableList<Pair<String, Int>>>()
        it.readLines().map {
            val (outerColor, interior) = it.split("bags contain")
            val innerColors = interior.split(",").map {
                val words = it.split(" ")
                if (words[1] == "no") return@map "" to 0
                val count = words[1].toInt()
                val color = words.subList(2, words.size - 1).joinToString(" ")
                color to count
            }
            outerColor.trim() to innerColors
        }.forEach { node ->
            node.second.filter { it.second != 0 }.forEach {
                if (node.first !in graph) {
                    graph[node.first] = mutableListOf()
                }
                graph[node.first]!!.add(it)
            }
        }

        val stack = Stack<Pair<String, Int>>()
        stack.add("shiny gold" to 1)
        var count = -1

        while (stack.isNotEmpty()) {
            val (current, currentNumber) = stack.pop()
            count += currentNumber
            for (next in graph[current] ?: listOf()) {
                stack.add(next.first to currentNumber * next.second)
            }
        }
        println(count)
    }
}