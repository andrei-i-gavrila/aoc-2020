package ro.codespace.aoc.day19

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups

interface Rule {
    val regexString: String
}

class BaseRule(val char: Char) : Rule {
    override val regexString by lazy {
        char.toString()
    }
}


class ComposedRule(val subRules: List<Int>, val allRules: Map<Int, Rule>) : Rule {
    override val regexString by lazy {
        subRules.map { allRules[it]!! }.joinToString("") { it.regexString }
    }
}

class DisjunctionRule(val subRules: List<Rule>) : Rule {
    override val regexString by lazy {
        subRules.joinToString("|", prefix = "(", postfix = ")") { it.regexString }
    }
}




fun main() {
    day(19) {
        val (rules, inputs) = it.readLines().readGroups()


        val allRules = mutableMapOf<Int, Rule>()
        rules.forEach {
            val ruleString = it.substringAfter(": ")
            val index = it.substringBefore(":").toInt()

            allRules[index] = when {
                ruleString.startsWith("\"") -> BaseRule(ruleString[1])
                ruleString.contains("|") -> {
                    DisjunctionRule(ruleString.split(" | ").map { ComposedRule(it.split(" ").map { it.toInt() }, allRules) })
                }
                else -> {
                    ComposedRule(ruleString.split(" ").map { it.toInt() }, allRules)
                }
            }
        }

        val regex = Regex(allRules[0]!!.regexString)
        inputs.filter {
            regex.matches(it)
        }.size.log()
    }
}