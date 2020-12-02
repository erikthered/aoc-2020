import java.io.File

fun getInputLines(fileName: String) = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()