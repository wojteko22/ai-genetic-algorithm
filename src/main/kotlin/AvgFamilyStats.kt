import java.io.FileWriter

private const val header = "nr,najlepszy,średni,najgorszy\n"

class AvgFamilyStats(params: AlgorithmParams, facilitiesData: FacilitiesData) {

    private val familiesStats = List(10) {
        val population = Population(params.populationSize, facilitiesData)
        population.makeFamily(params.numberOfGenerations, params.tournamentSize, params.crossingOdds)
    }

    private val numberOfGenerations = params.numberOfGenerations

    fun writeToCsv() {
        val fileWriter = FileWriter("stats.csv")
        fileWriter.use { it.write(csv) }
    }

    private val csv: String
        get() {
            var text = header
            for (index in 0 until numberOfGenerations) {
                text += printGenerationStats(index)
            }
            return text
        }

    private fun printGenerationStats(index: Int): String {
        val peersStats: PeersStats = familiesStats.map { it[index] }
        val average = peersStats.average()
        return "${index + 1},${average.csv}\n"
    }
}
