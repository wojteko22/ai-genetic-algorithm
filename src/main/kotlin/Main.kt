fun main(args: Array<String>) {
    val inputData = InputData.readFrom("src/main/resources/had12.dat")
    val population = Population(100, inputData)
    println(population.bestIndividual.cost)
}