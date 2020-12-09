fun main() {
    val input = getInputLines("day8.txt")
    val bootloader = Bootloader()

    if (bootloader.boot(input)) {
        println("Program completed succesfully")
    } else {
        println("Program terminated prematurely")
    }

    println("Part 1: accumulator value: ${bootloader.acc}")

    val candidates = setOf(*bootloader.visited.toTypedArray())
    for (c in candidates) {
        val modifiedInput = mutableListOf(*input.toTypedArray())
        when (modifiedInput[c].substring(0, 3)) {
            "acc" -> continue
            "jmp" -> modifiedInput[c] = modifiedInput[c].replace("jmp", "nop")
            "nop" -> modifiedInput[c] = modifiedInput[c].replace("nop", "jmp")
        }
        if (bootloader.boot(modifiedInput)) {
            break
        }
    }

    println("Part 2: accumulator value: ${bootloader.acc}")
}

fun argToInt(arg: String): Int {
    val sign = arg.substring(0, 1)
    val amt = arg.substring(1)

    return when (sign) {
        "+" -> amt.toInt()
        "-" -> arg.toInt()
        else -> throw RuntimeException("Invalid arg $arg")
    }
}

class Bootloader {
    private var cursor = 0
    private var done = false
    val visited = hashSetOf<Int>()
    var acc = 0

    fun boot(instructions: List<String>): Boolean {
        cursor = 0
        acc = 0
        visited.clear()
        while (!visited.contains(cursor)) {
            val (instruction, op) = instructions[cursor].split(" ")

            done = cursor == instructions.lastIndex
            visited += cursor

            when (instruction) {
                "acc" -> {
                    acc += argToInt(op)
                    cursor += 1
                }
                "jmp" -> cursor += argToInt(op)
                else -> cursor += 1
            }

            if (done) {
                break
            }
        }
        return done
    }
}