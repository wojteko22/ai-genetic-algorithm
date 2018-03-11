class AvgFamilyStats(private val familiesStats: Collection<FamilyStats>) {
    private val numberOfGenerations = familiesStats.first().size

    fun print() {
        for (index in 0 until numberOfGenerations) {
            printGenerationStats(index)
        }
    }

    private fun printGenerationStats(index: Int) {
        val peersStats: PeersStats = familiesStats.map { it[index] }
        val average = peersStats.average()
        println("$index: $average")
    }
}
