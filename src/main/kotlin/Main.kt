fun main(args: Array<String>) {
    val populationSize = 100
    val numberOfGenerations = 10
    val tournamentSize = 10

    val inputData = InputData.readFrom("src/main/resources/had12.dat")

    val familiesStats = List(10) {
        val population = Population(populationSize, inputData)
        population.makeFamily(numberOfGenerations, tournamentSize)
    }
    val avgFamilyStats = AvgFamilyStats(familiesStats)
    avgFamilyStats.print()
}
