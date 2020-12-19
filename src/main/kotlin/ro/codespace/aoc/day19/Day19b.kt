package ro.codespace.aoc.day19

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups


class SpecialRule8(val allRules: Map<Int, Rule>) : Rule {
    override val regexString by lazy {
        "(" + allRules[42]!!.regexString + ")+"
    }
}

class SpecialRule11(val allRules: Map<Int, Rule>) : Rule {
    override val regexString by lazy {
        (1..4).map {// manually searched the amount of times required to repeat by running until value did not change
            "(" + allRules[42]!!.regexString + "{${it}}" + allRules[31]!!.regexString + "{${it}})"
        }.joinToString("|", prefix = "(", postfix = ")")
    }
}

fun main() {
    day(19, "t2") {
        val (rules, inputs) = it.readLines().readGroups()


        val allRules = mutableMapOf<Int, Rule>()
        rules.forEach {
            val ruleString = it.substringAfter(": ")
            val index = it.substringBefore(":").toInt()


            allRules[index] = when {
                index == 8 -> SpecialRule8(allRules)
                index == 11 -> SpecialRule11(allRules)
                ruleString.startsWith("\"") -> BaseRule(ruleString[1])
                ruleString.contains("|") -> {
                    DisjunctionRule(ruleString.split(" | ").map { ComposedRule(it.split(" ").map { it.toInt() }, allRules) })
                }
                else -> {
                    ComposedRule(ruleString.split(" ").map { it.toInt() }, allRules)
                }
            }
        }

        val pattern = allRules[0]!!.regexString
        val regex = Regex(pattern)
        inputs.filter {
            regex.matches(it)
        }.size.log()
    }
}