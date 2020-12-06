fun main() {
    val input = getInputLines("day6.txt")

    // Part 1
    input
        .reduce { acc, s -> if (s.isNotBlank()) acc + s else "$acc," }
        .split(",")
        .map { it.toCharArray().distinct().size }
        .sum()
        .also(::println)

    // Part 2
    input
        .reduce { acc, s -> if (s.isNotBlank()) "$acc$s," else "$acc|" }
        .split("|")
        .map {
            it.split(",")
                .filter(String::isNotBlank)
                .map(String::toCharArray)
                .reduce { acc, s -> acc.intersect(s.asIterable()).toCharArray() }
                .size
        }.sum().also(::println)
}