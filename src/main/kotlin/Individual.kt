import java.util.*

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

    fun crossWith(partner: Individual): Individual {
        val genesOfThis = genes.subList(0, size / 2)
        val genesOfPartner = partner.genes - genesOfThis
        val genesOfChild = genesOfThis + genesOfPartner
        return Individual(facilitiesData, genesOfChild)
    }

    fun mutate(mutationOdds: Float): Individual {
        var newGenes = genes
        genes.indices.forEach { newGenes = tryToMutate(newGenes, it, mutationOdds) }
        return Individual(facilitiesData, newGenes)
    }

    private fun tryToMutate(genes: List<Int>, index: Int, mutationOdds: Float): List<Int> =
        if (shouldMutate(mutationOdds))
            mutate(genes, index)
        else
            genes

    private fun shouldMutate(mutationOdds: Float) = RandomDecisionMaker.shouldTakeAction(mutationOdds)

    private fun mutate(genes: List<Int>, index: Int): MutableList<Int> {
        val otherIndex = otherRandomIndex(index)
        return swap(genes, index, otherIndex)
    }

    private fun otherRandomIndex(index: Int): Int {
        val otherIndexes = genes.indices - index
        return otherIndexes.random
    }

    private fun swap(genes: List<Int>, index: Int, otherIndex: Int): MutableList<Int> {
        val newGenes = genes.toMutableList()
        val tmp = newGenes[index]
        newGenes[index] = newGenes[otherIndex]
        newGenes[otherIndex] = tmp
        return newGenes
    }
}

val <T> List<T>.random: T
    get() {
        val index = Random().nextInt(size)
        return this[index]
    }