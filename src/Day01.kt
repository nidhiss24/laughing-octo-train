fun main() {
    fun findDepthCount(input: List<Int>): Int {
        var prev: Int
        var curr: Int
        var counter = 0
        prev = input[0]
        for (elem in input) {
            curr = elem
            if (prev < curr) {
                counter++
            }
            prev = curr
        }
        return counter
    }

    fun part1(): Int {
        return findDepthCount(readInputAsInt("Day01_test"))
    }

    fun part2(): Int {
        return findDepthCount(transformToSlidingWindow(readInputAsInt("Day01_test")))
    }

    println(part1())
    println(part2())
}
