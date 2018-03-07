class Population(size: Int, inputData: InputData) {
    private val individuals = Array(size) { Individual(inputData) }

    val bestIndividual = individuals.minBy { it.cost } ?: throw NoSuchElementException("Empty population")
}