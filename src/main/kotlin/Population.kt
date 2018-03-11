import java.util.*
import kotlin.NoSuchElementException

class Population(private val individuals: List<Individual>) {

    private val size = individuals.size
    private val costs = individuals.map { it.cost }

    private val bestCost = costs.min() ?: handleEmptyPopulation()
    private val worstCost = costs.max() ?: handleEmptyPopulation()
    private val averageCost = costs.average()

    private val stats = PopulationStats(bestCost, averageCost, worstCost)

    constructor(size: Int, inputData: InputData) : this(List(size) { Individual(inputData) })

    private fun handleEmptyPopulation(): Nothing {
        throw NoSuchElementException("Empty population")
    }

    fun makeFamily(numberOfGenerations: Int, tournamentSize: Int): FamilyStats {
        var currentPopulation = this
        return List(numberOfGenerations) {
            val stats = currentPopulation.stats
            currentPopulation = currentPopulation.breed(tournamentSize)
            stats
        }
    }

    private fun breed(tournamentSize: Int): Population {
        val children = (1..size).map {
            val selected = selectInTournament(tournamentSize)
            selected.outcross(randomIndividual)
        }
        return Population(children)
    }

    private fun selectInTournament(tournamentSize: Int): Individual {
        val selectedGroup = individuals.shuffled().take(tournamentSize)
        return selectedGroup.maxBy { it.cost } ?: throw NoSuchElementException("No individuals in tournament")
    }

    private val randomIndividual: Individual
        get() {
            val nextInt = Random().nextInt(size)
            return individuals[nextInt]
        }
}