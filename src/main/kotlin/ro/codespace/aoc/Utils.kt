package ro.codespace.aoc


fun List<String>.readGroups(): List<List<String>> {
    val groups = mutableListOf<MutableList<String>>(mutableListOf())
    this.forEach {
        if (it.isEmpty()) {
            groups.add(mutableListOf())
        } else {
            groups.last().add(it)
        }
    }
    return groups
}