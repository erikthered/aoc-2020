fun main() {
    val input = getInputLines("day2.txt")

    println(PasswordEntry("foobar", 'o', 1..3).isValid())

    println(input.map {
        val params = it.split('-', ':', ' ')
        PasswordEntry(params[4], params[2].toCharArray().first(), params[0].toInt()..params[1].toInt())
    }.count { it.isValid() })

    println(input.map {
        val params = it.split('-', ':', ' ')
        PasswordEntry(params[4], params[2].toCharArray().first(), params[0].toInt()..params[1].toInt())
    }.count { it.isValid2() })
}

class PasswordEntry(
    val password: String,
    val requiredChar: Char,
    val requiredRange: IntRange) {

    fun isValid() = requiredRange.contains(password.toCharArray().filter { it == requiredChar }.count())

    fun isValid2() =
        when {
            password[requiredRange.first - 1] == requiredChar && password[requiredRange.last - 1] == requiredChar -> false
            password[requiredRange.first - 1] == requiredChar -> true
            password[requiredRange.last - 1] == requiredChar -> true
            else -> false
        }
}