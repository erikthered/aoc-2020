fun main() {
    val input = getInputLines("day7.txt")
    val graph = Graph<String>()
    input
        .map(::parseLine)
        .forEach { (parent, children) ->
            children.forEach { child -> graph.addEdge(child, parent) }
        }
    println(graph.toString())
    graph.depthTraversal("shiny gold").size.also { println("Part 1: ${it - 1} bags excluding self") }
}

fun parseLine(line: String): Pair<String, List<String>> {
    val (bag, contents) = line.split("contain")
    val bagColor = bag.removeSuffix(" bags ")
    if (contents == " no other bags.") {
        return bagColor to emptyList()
    }
    val innerBags = contents
        .removeSuffix(".")
        .split(", ")
        .map(String::trim)
        .map { it.removeSuffix(" bags").removeSuffix(" bag") }
        .map { it.substring(0, 1).toInt() to it.substring(2) }

    return bagColor to innerBags.map { it.second }
}