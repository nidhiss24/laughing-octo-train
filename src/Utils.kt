import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun readInputAsInt(name: String) = File("src", "$name.txt").readLines().map { it.toInt() }

fun transformToSlidingWindow(input: List<Int>): List<Int> {
    return input.windowed(size = 3, step = 1).map { it.sum() }
}

fun transformToInt(elem: String): Int {
    return elem.substringAfter(" ").toInt()
}
