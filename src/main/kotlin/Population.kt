class Population(size: Int) {
    private val individuals = Array(size) { Individual(inputData.size) }

    val bestIndividual = individuals.minBy { it.cost } ?: throw NoSuchElementException("Empty population")
}