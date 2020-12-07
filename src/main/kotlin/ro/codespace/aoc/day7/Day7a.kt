package ro.codespace.aoc.day7

import ro.codespace.aoc.day
import java.util.*

fun main() {
    listOf(day("7test"), day(7)).forEach {
        val graph = mutableMapOf<String, MutableList<String>>()
        it.readLines().map {
            val (outerColor, interior) = it.split("bags contain")
            val innerColors = interior.split(",").map {
                val words = it.split(" ")
                if (words[1] == "no") return@map 0 to ""
                val count = words[1].toInt()
                val color = words.subList(2, words.size - 1).joinToString(" ")
                count to color
            }
            outerColor.trim() to innerColors
        }.forEach { node ->
            node.second.filter { it.first != 0 }.forEach {
                if (it.second !in graph) {
                    graph[it.second] = mutableListOf()
                }
                graph[it.second]!!.add(node.first)
            }
        }

        val visited = mutableSetOf<String>()
        val stack = Stack<String>()
        stack.add("shiny gold")

        while (stack.isNotEmpty()) {
            val current = stack.pop()
            for (next in graph[current] ?: listOf()) {
                stack.add(next)
                visited.add(next)
            }
        }
        println(visited.size)
    }
}