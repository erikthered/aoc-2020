class Graph<T> {
    private val adjacencyMap = hashMapOf<T, HashSet<T>>()

    fun addEdge(source: T, dest: T) {
        adjacencyMap.computeIfAbsent(source) { HashSet() } += dest
        adjacencyMap.computeIfAbsent(dest) { HashSet() }
    }

    fun depthTraversal(root: T): Set<T> {
        val visited = linkedSetOf<T>()
        val deque = ArrayDeque<T>()
        deque.addFirst(root)
        while (deque.isNotEmpty()) {
            val vertex = deque.removeFirst()
            if (!visited.contains(vertex)) {
                visited.add(vertex)
                adjacencyMap.computeIfAbsent(vertex) { HashSet() }.forEach(deque::addFirst)
            }
        }
        return visited
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}

class IntWeightedGraph<T> {
    private val map = hashMapOf<T, HashMap<T, Int>>()

    fun addEdge(source: T, dest: Pair<T, Int>) {
        map.computeIfAbsent(source) { HashMap() } += dest
        map.computeIfAbsent(dest.first) { HashMap() }
    }

    fun sumFrom(source: T): Int {
        val edgeMap = map[source] ?: emptyMap()
        return edgeMap.map { (node, weight) ->
            val sum = sumFrom(node)
            weight * sum
        }.sum() + 1
    }
}
