import java.io.FileWriter

private const val header = "nr,najlepszy,średni,najgorszy,σ_najlepszy,σ_średni,σ_najgorszy\n"

class StatsPicker(private val params: AlgorithmParams, private val facilitiesData: FacilitiesData) {

    private val familiesStats = List(10) {
        val population = Population(params.populationSize, facilitiesData)
        population.makeFamily(
            params.numberOfGenerations,
            params.tournamentSize,
            params.crossingOdds,
            params.mutationOdds
        )
    }

    private val numberOfGenerations = params.numberOfGenerations

    private val csv: String
        get() {
            var text = header
            for (index in 0 until numberOfGenerations) {
                text += printGenerationStats(index)
            }
            return text
        }

    val fullStats: List<AverageStats>
        get() = (0 until numberOfGenerations).map { averageStats(it) }

    fun writeToCsv() {
        val numberOfFacilities = facilitiesData.numberOfFacilities
        val fileWriter = FileWriter("had${numberOfFacilities}_$params.csv")
        fileWriter.use { it.write(csv) }
    }

    private fun printGenerationStats(index: Int): String {
        val averageStats = averageStats(index)
        return "${index + 1},$averageStats\n"
    }

    private fun averageStats(generationIndex: Int): AverageStats = familiesStats.map { it[generationIndex] }.average()
}
