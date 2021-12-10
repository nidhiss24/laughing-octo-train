fun main() {

    fun part1(): Int {
        val input = readInput("Day02_test")
        val (horizontal, depth) = input.partition { it.contains("forward ") }
        val (down, up) = depth.partition { it.contains("down ") }
        return horizontal.sumOf { transformToInt(it) } * (
            down.sumOf { transformToInt(it) }
                .minus(up.sumOf { transformToInt(it) })
            )
    }

    data class Coordinates(val direction: String, val value: Int)

    fun part2(): Int {
        val input =
            readInput("Day02_test").map { Coordinates(it.substringBefore(" "), it.substringAfter(" ").toInt()) }
        var hor = 0
        var aim = 0
        var depth = 0
        input.forEach {
            val curr = it.value
            when (it.direction) {
                "forward" -> {
                    hor += curr
                    depth += aim * curr
                }
                "up" -> aim -= curr
                "down" -> aim += curr
            }
//            if (it.direction == "forward") {
//                hor += (curr)
//                depth += aim * curr
//            } else {
//                aim = if (it.direction == ("down")) aim + curr else aim - curr
//            }
        }

        return hor * depth
    }

    println(part1())
    println(part2())
}
