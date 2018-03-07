class Individual(size: Int) {
    private val genes = List(size) { it }.shuffled()

    fun print() {
        println(genes)
    }

    val cost = run {
        var result = 0
        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                result += inputData.flowMatrix[rowIndex][columnIndex] *
                        inputData.distanceMatrix[genes[rowIndex]][genes[columnIndex]]
            }
        }
        result
    }
}
