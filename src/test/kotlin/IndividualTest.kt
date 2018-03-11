import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IndividualTest {

    @Test
    fun getCost() {
        val inputData = FacilitiesData.readFrom("src/main/resources/had12.dat")
        val genes = listOf(3, 10, 11, 2, 12, 5, 6, 7, 8, 1, 4, 9)
        val individual = Individual(inputData, genes)
        assertThat(individual.cost).isEqualTo(1652)
    }

    @Test
    fun outcross() {
        val genes1 = listOf(1, 2, 3, 4)
        val genes2 = listOf(4, 1, 3, 2)
        val expectedGenes = listOf(1, 2, 4, 3)
        val inputData = FacilitiesData(4, emptyList(), emptyList())

        val individual1 = Individual(inputData, genes1)
        val individual2 = Individual(inputData, genes2)
        val child = individual1.outcross(individual2)
        val expectedChild = Individual(inputData, expectedGenes)

        assertThat(child).isEqualTo(expectedChild)
    }
}