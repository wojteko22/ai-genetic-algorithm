data class Individual(
    private val facilitiesData: FacilitiesData,
    private val genes: List<Int> = List(facilitiesData.size) { it + 1 }.shuffled()
) {

    private val size = facilitiesData.size

    val cost by lazy {
        var result = 0
        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                result += facilitiesData.flowMatrix[rowIndex][columnIndex] *
                        facilitiesData.distanceMatrix[genes[rowIndex] - 1][genes[columnIndex] - 1]
            }
        }
        result
    }

    fun outcross(partner: Individual): Individual {
        val genesOfThis = genes.subList(0, size / 2)
        val genesOfPartner = partner.genes - genesOfThis
        val genesOfChild = genesOfThis + genesOfPartner
        return Individual(facilitiesData, genesOfChild)
    }

    override fun toString(): String {
        return genes.toString()
    }
}