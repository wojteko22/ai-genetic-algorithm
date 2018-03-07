data class Individual(
    private val inputData: InputData,
    private val genes: List<Int> = List(inputData.size) { it + 1 }.shuffled()
) {

    private val size = inputData.size

    val cost by lazy {
        var result = 0
        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                result += inputData.flowMatrix[rowIndex][columnIndex] *
                        inputData.distanceMatrix[genes[rowIndex] - 1][genes[columnIndex] - 1]
            }
        }
        result
    }

    fun outcross(partner: Individual): Individual {
        val genesOfThis = genes.subList(0, size / 2)
        val genesOfPartner = partner.genes - genesOfThis
        val genesOfChild = genesOfThis + genesOfPartner
        return Individual(inputData, genesOfChild)
    }

    override fun toString(): String {
        return genes.toString()
    }
}