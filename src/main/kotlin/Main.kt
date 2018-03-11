fun main(args: Array<String>) {
    val params = AlgorithmParams()
    val facilitiesData = FacilitiesData.readFrom("src/main/resources/had12.dat")
    val familiesStats = makeSomeFamilies(params, facilitiesData)
    val avgFamilyStats = AvgFamilyStats(familiesStats)
    avgFamilyStats.print()
}

private fun makeSomeFamilies(params: AlgorithmParams, facilitiesData: FacilitiesData) = List(10) {
    val population = Population(params.populationSize, facilitiesData)
    population.makeFamily(params.numberOfGenerations, params.tournamentSize)
}

class AlgorithmParams(val populationSize: Int = 100, val numberOfGenerations: Int = 100, val tournamentSize: Int = 5)
