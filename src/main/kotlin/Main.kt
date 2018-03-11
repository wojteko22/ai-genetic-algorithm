fun main(args: Array<String>) {
    val populationSize = 100
    val inputData = InputData.readFrom("src/main/resources/had12.dat")
    val population = Population(populationSize, inputData)
    val familyStats = population.makeFamily(10, 5)
    println(familyStats)
}

class AvgFamilyStats(val familiesStats: Set<FamilyStats>)