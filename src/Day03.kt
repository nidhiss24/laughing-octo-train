fun main() {

    // trying to use more kotlin built in methods

    data class BitFrequency(val leastCommon: Int, val mostCommon: Int)

    fun getBitFrequencyMapPerColumn(input: List<String>, columns: Int): Map<Int, Int> {
        return input.map {
            it.subSequence(columns, it.length)
        }.groupingBy {
            it.first().toString().toInt(2)
        }.eachCount()
    }

    fun transformToBitFrequency(mapOfBitColumns: Map<Int, Int>): List<BitFrequency> {
        val factorToProcess = mapOfBitColumns.values.maxOrNull()!!
        return mapOfBitColumns.entries.filter { it.value == factorToProcess }.map { BitFrequency(it.key.xor(1), it.key) }.toList()
    }

    fun getGamma(input: List<String>): String {
        var gamma = ""
        for (columns in input.first().indices) {
            val bitFrequencyMapPerColumn = getBitFrequencyMapPerColumn(input, columns)
            gamma += transformToBitFrequency(bitFrequencyMapPerColumn).first().mostCommon.toString()
        }
        return gamma
    }

    fun generationRate(isO2GenerationRate: Boolean, input: List<String>): Int {
        var transformInputToRates = input
        for (columns in input.first().indices) {
            if (transformInputToRates.size > 1) {
                val bitFrequency =
                    transformToBitFrequency(
                        getBitFrequencyMapPerColumn(transformInputToRates, columns)
                    )
                var bitToProcess = 0
                when (isO2GenerationRate) {
                    true -> {
                        bitToProcess = bitFrequency.map { it.mostCommon }.first()
                        if (bitFrequency.size > 1) {
                            bitToProcess = 1
                        }
                    }
                    false -> {
                        bitToProcess = bitFrequency.map { it.leastCommon }.first()
                        if (bitFrequency.size > 1) {
                            bitToProcess = 0
                        }
                    }
                }

                transformInputToRates =
                    transformInputToRates.filter { it.substring(columns, columns + 1) == (bitToProcess.toString()) }
                        .toMutableList()
            }
        }
        return transformInputToRates.first().toInt(2)
    }

    fun part1(): Int {
        val input = readInput("Day03_test")
        val gamma = getGamma(input)
        val gammaToInt = gamma.toInt(2)
        val epsilon = gamma.map {
            it.toString().toInt(2).xor(1)
        }.joinToString("").trim().toInt(2)

        return (epsilon * gammaToInt)
    }

    fun part2(): Int {
        val input = readInput("Day03_test")
        val o2Rate = generationRate(true, input)
        val co2Rate = generationRate(false, input)
        println(co2Rate)
        println(o2Rate)
        return o2Rate * co2Rate
    }

    println(part1())
    println(part2())
}
