import java.util.*
import kotlin.NoSuchElementException
import kotlin.math.roundToInt

class Population(private val individuals: List<Individual>) {

    private val size = individuals.size
    private val costs = individuals.map { it.cost }

    private val bestCost = costs.min() ?: handleEmptyPopulation()
    private val worstCost = costs.max() ?: handleEmptyPopulation()
    private val averageCost = costs.average().roundToInt()

    private val stats = PopulationStats(bestCost, averageCost, worstCost)

    constructor(size: Int, facilitiesData: FacilitiesData) : this(List(size) { Individual(facilitiesData) })

    private fun handleEmptyPopulation(): Nothing {
        throw NoSuchElementException("Empty population")
    }

    fun makeFamily(numberOfGenerations: Int, tournamentSize: Int, crossingOdds: Float): FamilyStats {
        var currentPopulation = this
        return List(numberOfGenerations) {
            val stats = currentPopulation.stats
            currentPopulation = currentPopulation.breed(tournamentSize, crossingOdds)
            stats
        }
    }

    private fun breed(tournamentSize: Int, crossingOdds: Float): Population {
        val children = (1..size).map {
            val selected = selectInTournament(tournamentSize)
            giveDescendantOf(selected, crossingOdds)
        }
        return Population(children)
    }

    private fun giveDescendantOf(individual: Individual, crossingOdds: Float) =
        if (shouldCross(crossingOdds)) {
            individual.crossWith(randomIndividual)
        } else {
            individual
        }

    private fun shouldCross(crossingOdds: Float) = Random().nextFloat() < crossingOdds

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