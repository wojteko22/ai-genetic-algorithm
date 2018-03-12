import java.io.FileWriter

private const val header = "nr,najlepszy,średni,najgorszy,σ_najlepszy,σ_średni,σ_najgorszy\n"

class AvgFamilyStats(params: AlgorithmParams, facilitiesData: FacilitiesData) {

    private val familiesStats = List(10) {
        val population = Population(params.populationSize, facilitiesData)
        population.makeFamily(
            params.numberOfGenerations,
            params.tournamentSize,
            params.crossingOdds,
            params.mutationOdds
        )
    }

    private val paramsInfo = with(params) {
        "pop_size=$populationSize,gen=$numberOfGenerations,Px=$crossingOdds,Pm=$mutationOdds,Tour=$tournamentSize"
    }

    private val numberOfGenerations = params.numberOfGenerations

    fun writeToCsv(dataNumber: Int) {
        val fileWriter = FileWriter("had${dataNumber}_$paramsInfo.csv")
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
        val averageStats = peersStats.average()
        return "${index + 1},$averageStats\n"
    }
}
