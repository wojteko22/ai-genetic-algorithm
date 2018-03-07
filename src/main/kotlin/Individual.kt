class Individual(size: Int) {
    private val genes = List(size) { it + 1 }.shuffled()

    val cost = run {
        var result = 0
        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                result += inputData.flowMatrix[rowIndex][columnIndex] *
                        inputData.distanceMatrix[genes[rowIndex] - 1][genes[columnIndex] - 1]
            }
        }
        result
    }
}
