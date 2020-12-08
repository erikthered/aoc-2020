import java.io.File

fun getInputLines(fileName: String) = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()

fun getInputString(fileName: String) = File(ClassLoader.getSystemResource(fileName).toURI()).readText()