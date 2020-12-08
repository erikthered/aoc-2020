fun main() {
    val input = getInputLines("day7.txt")
    val graph = Graph<String>()
    val weightedGraph = IntWeightedGraph<String>()
    input
        .map(::parseLine)
        .forEach { (parent, children) ->
            children.forEach { child ->
                graph.addEdge(child.color, parent)
                weightedGraph.addEdge(parent, child.color to child.quantity)
            }
        }
    graph.depthTraversal("shiny gold").size.also { println("Part 1: ${it - 1} bags excluding self") }
    weightedGraph.sumFrom("shiny gold").also { println("Part 2: ${it - 1} bags") }
}

fun parseLine(line: String): Pair<String, List<ContainedBag>> {
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
        .map { ContainedBag(it.substring(0, 1).toInt(), it.substring(2)) }

    return bagColor to innerBags
}

class ContainedBag(val quantity: Int, val color: String)