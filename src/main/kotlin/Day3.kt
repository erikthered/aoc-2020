fun main() {
    val grid = getInputLines("day3.txt").map { it.toCharArray() }

    // Part 1
    computeNumberOfTrees(grid, Slope(1, 3)).also { println(it) }

    val slopes = sequenceOf(Slope(1,1), Slope(1,3), Slope(1, 5), Slope(1, 7), Slope(2, 1))

    slopes.map { computeNumberOfTrees(grid, it) }.forEach { println(it) }

    // Part 2
    slopes.map { computeNumberOfTrees(grid, it) }.map { it.toLong() }.reduce(Math::multiplyExact).also { println(it) }
}

class Coordinate(var x: Int, var y: Int)

class Slope(val y: Int, val x: Int)

fun computeNumberOfTrees(grid: List<CharArray>, slope: Slope): Int {
    val position = Coordinate(0, 0)
    var counter = 0
    while (position.y < grid.size) {
        val row = grid[position.y]

        val x = if (position.x >= row.size) position.x % row.size else position.x

        if(row[x] == '#') counter++

        position.x += slope.x
        position.y += slope.y
    }
    return counter
}