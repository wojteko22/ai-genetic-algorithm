class Population(size: Int, inputData: InputData) {
    private val individuals = Array(size) { Individual(inputData) }

    val bestCost = individuals
        .map { it.cost }
        .min() ?: throw NoSuchElementException("Empty population")
}