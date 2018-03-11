fun main(args: Array<String>) {
    val populationSize = 100
    val tournamentSize = 5

    val inputData = InputData.readFrom("src/main/resources/had12.dat")
    val population = Population(populationSize, inputData)
    println(population.bestCost)
    val newPopulation = population.breed(tournamentSize)
    println(newPopulation.bestCost)
}
