import java.util.*
import kotlin.NoSuchElementException

class Population(private val individuals: List<Individual>) {

    private val size = individuals.size

    constructor(size: Int, inputData: InputData) : this(List(size) { Individual(inputData) })

    val bestCost = individuals
        .map { it.cost }
        .min() ?: throw NoSuchElementException("Empty population")

    fun breed(tournamentSize: Int): Population {
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